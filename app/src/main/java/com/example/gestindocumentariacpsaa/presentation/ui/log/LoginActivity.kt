package com.example.gestindocumentariacpsaa.presentation.ui.log

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.data.preferences.UserPreferences
import com.example.gestindocumentariacpsaa.databinding.ActivityLoginBinding
import com.example.gestindocumentariacpsaa.presentation.ui.loading.LoadingActivity
import com.example.gestindocumentariacpsaa.presentation.utils.Messages
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        initNavigation()
        initLogin()
    }

    private fun initNavigation() {
        binding.apply {
            //Validar Login
            btLogin.setOnClickListener {
                val user = binding.etUsuario.text.toString()
                val password = binding.etPassword.text.toString()
                viewModel.loginValidation(user = user, password = password)
            }
        }
    }

    private fun navigateTo(activity: Class<out AppCompatActivity>) {
        val i = Intent(this, activity)
        startActivity(i)
        finish()
    }

    private fun initLogin() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginResult.collect { dataLogin ->
                    when (dataLogin) {
                        LoginState.Nothing -> manageLoading(false)
                        LoginState.Loading -> manageLoading(true)
                        is LoginState.ErrorEmptyFields -> errorState(
                            false,
                            this@LoginActivity,
                            dataLogin.errorMessage
                        )

                        is LoginState.ErrorUser -> errorState(
                            false,
                            this@LoginActivity,
                            dataLogin.errorMessage
                        )

                        is LoginState.Success -> successState(
                            false,
                            this@LoginActivity,
                            dataLogin.successMessage,
                            dataLogin.colaborador,
                            LoadingActivity::class.java
                        )
                    }
                }
            }
        }
    }

    private fun successState(
        state: Boolean,
        context: Context,
        message: String,
        colaborador:Response.ColaboradorInfo,
        activity: Class<out AppCompatActivity>
    ) {
        manageLoading(state)
        Messages.genericToast(context, message)
        navigateTo(activity = activity)
        UserPreferences.setUser(this, usuario = colaborador)
    }

    private fun errorState(state: Boolean, context: Context, message: String) {
        manageLoading(state)
        Messages.genericToast(context, message)
    }

    private fun manageLoading(state: Boolean) {
        binding.pb.isVisible = state
    }

}