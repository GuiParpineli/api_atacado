package com.app.atacado.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @NotNull
    @Size(min = 3, max=8)
    val name: String = "",
    @NotNull
    val email: String = "",
    @NotNull
    @Size(min=4, max = 10)
    val password: String = ""
)
