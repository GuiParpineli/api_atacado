package com.app.atacado.service

import com.app.atacado.exceptions.ResourceNotFoundException
import com.app.atacado.model.SystemUser
import com.app.atacado.model.SystemUserRoles
import com.app.atacado.model.User
import com.app.atacado.repository.SystemUserRepository
import com.app.atacado.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository,
    val systemUserRepository: SystemUserRepository,
    val mapper: ObjectMapper
) {

    fun getAll(): ResponseEntity<Any> {
        val saved = repository.findAll()
        if (saved.isEmpty()) ResponseEntity("Nenhum usuario cadastrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    fun get(id: Long): ResponseEntity<Any> {
        val saved = repository.findById(id).orElseThrow {
            ResourceNotFoundException(
                "Nenhum usuario com o id " +
                        "informado"
            )
        }
        return ResponseEntity.ok(saved)
    }

    fun save(user: User): ResponseEntity<Any> {
        val saved = repository.save(user)
        systemUserRepository.save(
            SystemUser(
                null, user.name, user.name, user.email, user.password, SystemUserRoles.ROLE_USER
            )
        )
        return ResponseEntity.ok(saved)
    }

    fun update(user: User): User = repository.saveAndFlush(user)

    fun delete(id: Long) = repository.deleteById(id)

}