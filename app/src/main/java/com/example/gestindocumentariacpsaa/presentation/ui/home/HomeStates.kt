package com.example.gestindocumentariacpsaa.presentation.ui.home

import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.presentation.models.HomeModel

sealed class HomeStates {
    data object Loading : HomeStates()
    data class Success(val datos: List<String>) : HomeStates()
    data class Error(val error: String) : HomeStates()
}
