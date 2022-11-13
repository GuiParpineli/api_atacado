package com.app.atacado.service

import com.app.atacado.exceptions.UserSaveException
import com.app.atacado.model.SystemUser
import com.app.atacado.repository.SystemUserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class SystemUserService(val repository: SystemUserRepository) : UserDetailsService {

    fun getAll(): ResponseEntity<Any> {
        val systemUserList: List<SystemUser> = repository.findAll()
        if (systemUserList.isEmpty()) ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(systemUserList)
    }

    fun get(id: Long): ResponseEntity<Any> {
        val saved: Optional<SystemUser> = repository.findById(id)
        if (saved.isEmpty) return ResponseEntity("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    @Throws(UserSaveException::class)
    fun save(systemUser: SystemUser): ResponseEntity<Any> {
        val user: SystemUser
        try { user = repository.save(systemUser)}
        catch (e: Exception) { throw UserSaveException("Não foi possivel salvar o usuario, tente novamente") }
        return ResponseEntity.ok(user)
    }

    fun update(SystemUser: SystemUser): SystemUser = repository.saveAndFlush(SystemUser)

    fun delete(id: Long) = repository.deleteById(id)

    override fun loadUserByUsername(username: String): SystemUser = repository.findByUsername(username)

}