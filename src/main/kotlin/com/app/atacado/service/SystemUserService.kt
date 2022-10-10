package com.app.atacado.service

import com.app.atacado.model.SystemUser
import com.app.atacado.repository.SystemUserRepository
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SystemUserService(val repository: SystemUserRepository) : UserDetailsService{

    fun getAll(): List<SystemUser> = repository.findAll()

    fun get(id: Long): Optional<SystemUser> = repository.findById(id)

    fun save(SystemUser: SystemUser): SystemUser = repository.save(SystemUser)

    fun update(SystemUser: SystemUser): SystemUser = repository.saveAndFlush(SystemUser)

    fun delete(id: Long) = repository.deleteById(id)

    override fun loadUserByUsername(username: String): SystemUser = repository.findByUsername(username)

}