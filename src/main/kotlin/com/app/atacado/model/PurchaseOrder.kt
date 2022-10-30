package com.app.atacado.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.Date
import javax.persistence.*


@Entity
data class PurchaseOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    val id: Long? = null,

    @ManyToMany
    @JoinTable(
        name = "order_product",
        joinColumns = [JoinColumn(name = "id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "id_product", referencedColumnName = "id")]
    )
    val product: List<Product> = mutableListOf(),

    @ManyToOne
    val vendor: Vendor = Vendor(),

    @ManyToOne
    val customer: Customer = Customer(),

    val date: Date = Date()
)