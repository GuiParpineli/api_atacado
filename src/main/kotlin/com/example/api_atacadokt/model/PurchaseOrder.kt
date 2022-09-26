package com.example.api_atacadokt.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
data class PurchaseOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "order_product",
        joinColumns = [JoinColumn(name = "id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_product", referencedColumnName = "id")]
    )
    @JsonIgnoreProperties("Product")
    val product: List<Product> = mutableListOf()
)