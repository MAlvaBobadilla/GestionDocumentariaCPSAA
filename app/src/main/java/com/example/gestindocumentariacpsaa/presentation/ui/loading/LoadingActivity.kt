package com.example.gestindocumentariacpsaa.presentation.ui.loading

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.gestindocumentariacpsaa.databinding.ActivityLoadingBinding
import com.example.gestindocumentariacpsaa.presentation.ui.home.HomeFragment
import com.example.gestindocumentariacpsaa.presentation.ui.home.HomeViewModel
import com.example.gestindocumentariacpsaa.presentation.ui.main.MainActivity
import com.example.gestindocumentariacpsaa.presentation.utils.Messages
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoadingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        initCount()
    }

    private fun initCount() {
        lifecycleScope.launch {
            delay(3000)
            val i = Intent(this@LoadingActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }


}