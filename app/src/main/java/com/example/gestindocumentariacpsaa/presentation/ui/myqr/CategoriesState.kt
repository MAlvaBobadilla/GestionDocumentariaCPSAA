package com.example.gestindocumentariacpsaa.presentation.ui.myqr

import com.example.gestindocumentariacpsaa.presentation.models.CategoriesModel

sealed class CategoriesState {
    data object Loading : CategoriesState()
    data class Success(val categories: List<CategoriesModel>) : CategoriesState()
    data class Error(val error: String) : CategoriesState()
}