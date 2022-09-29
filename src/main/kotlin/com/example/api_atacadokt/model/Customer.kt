package com.example.api_atacadokt.model

import jakarta.persistence.*

@Entity
data class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    var id: Long? = null,
    var cnpj: String = "",
    var razaoSocial: String = "",
    var nomeFantasia: String = "",
    var email: String = "",
    var password: String = ""
)

