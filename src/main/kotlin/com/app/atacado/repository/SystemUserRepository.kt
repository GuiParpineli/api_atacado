package com.app.atacado.repository

import com.app.atacado.model.SystemUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SystemUserRepository : JpaRepository<SystemUser, Long> {
    fun findByUsername(username: String): SystemUser
}