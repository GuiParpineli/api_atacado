package com.app.atacado.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


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
