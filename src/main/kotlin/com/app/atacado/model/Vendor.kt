package com.app.atacado.model

import javax.persistence.*


@Entity
data class Vendor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String = "",
    @Column(nullable = false, unique = true)
    var cpf: String = "",
    var comission: Int = 10
)
