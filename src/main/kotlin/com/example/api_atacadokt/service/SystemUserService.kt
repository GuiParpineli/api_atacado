package com.example.api_atacadokt.service

import com.example.api_atacadokt.model.SystemUser
import com.example.api_atacadokt.repository.SystemUserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class SystemUserService(val repository: SystemUserRepository) {

    fun getAll(): List<SystemUser> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<SystemUser> {
        return repository.findById(id)
    }

    fun save(SystemUser: SystemUser): SystemUser {
        return repository.save(SystemUser)
    }

    fun update(SystemUser: SystemUser): SystemUser {
        return repository.saveAndFlush(SystemUser)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

    fun findByUsername(username: String): Optional<SystemUser> {
        return repository.findByUsername(username)
    }
}