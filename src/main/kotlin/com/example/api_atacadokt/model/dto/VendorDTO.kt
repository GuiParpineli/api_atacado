package com.example.api_atacadokt.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class VendorDTO(
    var name: String = "",
    var comission: Int = 10
)