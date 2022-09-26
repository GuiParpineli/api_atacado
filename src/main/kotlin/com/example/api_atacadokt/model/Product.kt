package com.example.api_atacadokt.model

import jakarta.persistence.*

@Entity
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String = "",
    var ncm: String = "",
    var category: String = "",
    var inventory: Int = 0,
    var pricePurchase: Double = 0.0,
    var price: Double = 0.0,

)
