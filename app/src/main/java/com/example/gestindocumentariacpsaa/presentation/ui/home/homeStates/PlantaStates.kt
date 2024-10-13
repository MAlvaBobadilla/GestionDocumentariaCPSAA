package com.example.gestindocumentariacpsaa.presentation.ui.home.homeStates

import com.example.gestindocumentariacpsaa.data.models.Response

sealed class PlantaStates {
    data object Loading : PlantaStates()
    data class Success(val plantas: List<Response.Plantas> = emptyList(), val successMessage: String) : PlantaStates()
    data class Error(val error: String) : PlantaStates()
}
