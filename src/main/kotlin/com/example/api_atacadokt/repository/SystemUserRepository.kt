package com.example.api_atacadokt.repository

import com.example.api_atacadokt.model.SystemUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface SystemUserRepository : JpaRepository<SystemUser, Long> {

    fun findByUsername(username: String): Optional<SystemUser>

}