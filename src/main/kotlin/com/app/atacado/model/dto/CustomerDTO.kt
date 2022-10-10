package com.app.atacado.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CustomerDTO(
    val cnpj: String = "",
    val razaoSoxial: String = "",
    val nomeFantasia: String = ""
)
