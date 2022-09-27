package com.example.api_atacadokt.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CustomerDTO(
    var cnpj: String = "",
    var razaoSocial: String = "",
    var nomeFantasia: String = ""
)
