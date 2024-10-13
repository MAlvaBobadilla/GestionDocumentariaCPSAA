package com.example.gestindocumentariacpsaa.data.models

import com.example.gestindocumentariacpsaa.domain.model.PlantaModel

data class Response(
    var ok: Int = 0,
    var message: String = "",
    var colaborador: ColaboradorInfo?,
    var plantas: List<Plantas> = emptyList(),
    var areas: List<String> = emptyList()
) {
    data class ColaboradorInfo(
        var apellidoPaterno: String = "",
        var apellidoMaterni: String = "",
        var nombres: String = "",
        var nombreCompleto: String = "",
        var dni: String = "",
        var puesto: String = "",
        var superintendencia: String = "",
        var area: String = "",
        var planta: String = "",
        var fecNacimiento: String = "",
        var edad: Int = 0,
        var jefeDirecto: String = ""
    )

    data class Plantas(
        var id: String,
        var planta: String
    )

    data class Areas(
        var id: String,
        var area: String
    )
}
