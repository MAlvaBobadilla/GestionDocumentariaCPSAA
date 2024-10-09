package com.example.gestindocumentariacpsaa.presentation.ui.myqr

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestindocumentariacpsaa.domain.usecase.GenericGet
import com.example.gestindocumentariacpsaa.presentation.ui.myqr.CategoriesState.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoriesViewModel : ViewModel() {
    private var _state = MutableStateFlow<CategoriesState>(Loading)
    val state: StateFlow<CategoriesState> = _state

    private val getAnimesUC = GenericGet()

    fun getCategories() {

    }
}