package com.example.gestindocumentariacpsaa.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestindocumentariacpsaa.data.models.Response
import com.example.gestindocumentariacpsaa.domain.usecase.GenericGet
import com.example.gestindocumentariacpsaa.presentation.ui.home.homeStates.AreaStates
import com.example.gestindocumentariacpsaa.presentation.ui.home.homeStates.PlantaStates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {

    private var _plantaState = MutableStateFlow<PlantaStates>(PlantaStates.Loading)
    val plantaState: StateFlow<PlantaStates> = _plantaState
    private var _areaState = MutableStateFlow<AreaStates>(AreaStates.Loading)
    val areaState: StateFlow<AreaStates> = _areaState

    var plantas: List<Response.Plantas>? = null

    private val getCombos = GenericGet()

    fun getPlantas() {
        viewModelScope.launch {
            _plantaState.value = PlantaStates.Loading
            val responsePlantas = withContext(Dispatchers.IO) { getCombos.getPlantas() }
            try {
                if (responsePlantas!!.ok != 0) {
                    var reponseUnique = responsePlantas.plantas.toSet()
                    plantas = reponseUnique.toList()
                    _plantaState.value = PlantaStates.Success(
                        plantas = plantas!!,
                        successMessage = "Plantas de Labor obtenidas exitosamente"
                    )
                } else {
                    _plantaState.value = PlantaStates.Error("Error al obtener las Plantas de Labor")
                }
            } catch (e: Exception) {
                _plantaState.value = PlantaStates.Error(e.message.toString())
            }

        }
    }

    fun getAreas(planta: String) {
        viewModelScope.launch {
            _areaState.value = AreaStates.Loading
            if (planta.isEmpty()) {
                _areaState.value = AreaStates.Error("Seleccione una Planta")
                return@launch
            } else {
                val responseAreas =
                    withContext(Dispatchers.IO) { getCombos.getAreas(planta = planta) }
                try {
                    if (responseAreas!!.ok != 0) {
                        val responseUnique = responseAreas.areas.toSet()
                        var areas = responseUnique.toList().sorted()
                        _areaState.value = AreaStates.Success(
                            areas = areas,
                            successMessage = "Áreas de labor obtenidas para la planta : $planta"
                        )
                    } else {
                        _areaState.value =
                            AreaStates.Error("Error al obtener las Areas de Labor para la planta $planta")
                    }
                } catch (e: Exception) {
                    _areaState.value = AreaStates.Error(e.message.toString())
                }
            }
        }
    }

}