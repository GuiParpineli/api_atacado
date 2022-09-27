package com.example.api_atacadokt.controller

import com.example.api_atacadokt.model.SystemUser
import com.example.api_atacadokt.service.SystemUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class SystemUserController(val service: SystemUserService) {

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val SystemUserList: List<SystemUser> = service.getAll()
        if (SystemUserList.isEmpty())
            return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(SystemUserList)
    }

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long): Any {
        val saved: Optional<SystemUser> = service.get(id)
        if (saved.isEmpty)
            return ResponseEntity("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    @PostMapping("/cadastrar")
    fun save(@RequestBody systemUser: SystemUser) = service.save(systemUser)

    @PostMapping("/login")
    fun login(@RequestBody systemUser: SystemUser): ResponseEntity<Any> {
        return ResponseEntity.ok("ok")
    }

    @PutMapping
    fun update(@RequestBody systemUser: SystemUser) = service.update(systemUser)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}