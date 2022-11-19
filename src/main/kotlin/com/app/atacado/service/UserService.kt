package com.app.atacado.service

import com.app.atacado.repository.SystemUserRepository
import com.app.atacado.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Service
class UserService(
    val repository: UserRepository,
    val systemUserRepository: SystemUserRepository,
    val mapper: ObjectMapper
) {

   fun getAll(): ResponseEntity<Any> {
       val saved = repository.findAll()
       if(saved.isEmpty()) ResponseEntity("Nenhum usuario cadastrado", HttpStatus.NOT_FOUND)
       return ResponseEntity.ok(saved)
   }

}