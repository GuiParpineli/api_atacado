package com.app.atacado.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VendorDTO(
    val name: String = "",
    val comission: Int = 0
)
