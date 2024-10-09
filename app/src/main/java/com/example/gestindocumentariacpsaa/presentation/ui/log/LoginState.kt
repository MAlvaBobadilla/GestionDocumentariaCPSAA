package com.example.gestindocumentariacpsaa.presentation.ui.log

sealed class LoginState{
    data object Nothing: LoginState()
    data object Loading : LoginState()
    data class ErrorEmptyFields(val errorMessage: String) : LoginState()
    data class ErrorUser(val errorMessage: String): LoginState()
    data class Success(val successMessage: String, val user: String): LoginState()
}
