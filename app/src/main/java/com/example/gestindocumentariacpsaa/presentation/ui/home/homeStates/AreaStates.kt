package com.example.gestindocumentariacpsaa.presentation.ui.home.homeStates

sealed class AreaStates {
    data object Loading : AreaStates()
    data class Success(val areas: List<String> = emptyList(), val successMessage: String) : AreaStates()
    data class Error(val error: String) : AreaStates()
}