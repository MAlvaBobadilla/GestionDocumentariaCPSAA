package com.example.gestindocumentariacpsaa.presentation.ui.log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestindocumentariacpsaa.domain.usecase.GenericGet
import com.example.gestindocumentariacpsaa.presentation.ui.log.LoginState.ErrorEmptyFields
import com.example.gestindocumentariacpsaa.presentation.ui.log.LoginState.ErrorUser
import com.example.gestindocumentariacpsaa.presentation.ui.log.LoginState.Loading
import com.example.gestindocumentariacpsaa.presentation.ui.log.LoginState.Nothing
import com.example.gestindocumentariacpsaa.presentation.ui.log.LoginState.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel : ViewModel() {
    private val _loginResult = MutableStateFlow<LoginState>(Nothing)
    var loginResult: StateFlow<LoginState> = _loginResult

    private val getValidationUC = GenericGet()

    fun loginValidation(user: String?, password: String?) {
        viewModelScope.launch {
            _loginResult.value = Loading
            when {
                user.isNullOrEmpty() || password.isNullOrEmpty() -> {
                    _loginResult.value =
                        ErrorEmptyFields("Complete los campos Usuario y Contraseña")
                }

                user != password -> {
                    _loginResult.value = ErrorUser("Usuario y/o Contraseña Incorrectos")
                }

                else -> {
                    val response =
                        withContext(Dispatchers.IO) { getValidationUC.getUserValidation(id = user) }

                    try {
                        if (response!!.ok == 0) {
                            _loginResult.value = ErrorUser(response.message)
                        } else {
                            _loginResult.value = Success(
                                successMessage = "BIENVENIDO ${response.colaborador!!.nombreCompleto}",
                                user = response.colaborador!!.dni,
                                colaborador = response.colaborador!!
                            )
                        }
                    } catch (e: Exception) {
                        _loginResult.value = ErrorUser("Error de Servidor")
                    }



                }
            }
        }
    }
}