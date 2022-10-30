package com.app.atacado.model

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
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

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.YYYY HH:mm:ss"))

)
