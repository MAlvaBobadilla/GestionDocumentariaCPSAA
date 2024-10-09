package com.example.gestindocumentariacpsaa.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestindocumentariacpsaa.domain.usecase.GenericGet
import com.example.gestindocumentariacpsaa.presentation.ui.home.HomeStates.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private var _state = MutableStateFlow<HomeStates>(Loading)
    val state: StateFlow<HomeStates> = _state

    private val getCombos = GenericGet()

    fun getPlantas() {
        viewModelScope.launch {
            _state.value = Loading
            val responsePlantas = withContext(Dispatchers.IO) { getCombos.getPlantas() }
            try {
                if (responsePlantas!!.ok != 0) {
                    _state.value = HomeStates.Success(
                        datos = responsePlantas.plantas
                    )
                } else {
                    _state.value = HomeStates.Error("Error al obtener las Plantas de Labor")
                }
            } catch (e: Exception) {
                _state.value = HomeStates.Error(e.message.toString())
            }

        }
    }

    fun getAreas(planta: String) {
        viewModelScope.launch {
            _state.value = Loading
            if (planta.isEmpty()) {
                _state.value = HomeStates.Error("Seleccione una Planta")
                return@launch
            } else {
                val responseAreas =
                    withContext(Dispatchers.IO) { getCombos.getAreas(planta = planta) }
                try {
                    if (responseAreas!!.ok != 0) {
                        _state.value = HomeStates.Success(
                            datos = responseAreas.areas
                        )
                    } else {
                        _state.value =
                            HomeStates.Error("Error al obtener las Areas de Labor para la planta $planta")
                    }
                } catch (e: Exception) {
                    _state.value = HomeStates.Error(e.message.toString())
                }
            }
        }
    }

}