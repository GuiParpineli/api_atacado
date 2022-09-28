package com.example.api_atacadokt.controller

import com.example.api_atacadokt.model.SystemUser
import com.example.api_atacadokt.security.JwtUtil
import com.example.api_atacadokt.service.SystemUserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class SystemUserController(
    val service: SystemUserService,
    val authenticationManager: AuthenticationManager,
    val jwtUtil: JwtUtil
) {

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val systemUserList: List<SystemUser> = service.getAll()
        if (systemUserList.isEmpty())
            return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(systemUserList)
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
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    systemUser.username,
                    systemUser.password
                )
            )
        } catch (e: BadCredentialsException) {
            throw BadCredentialsException("usuario invalido")
        }

        val userDetails: UserDetails = service.loadUserByUsername(systemUser.username)
        val jwt: String = jwtUtil.generateToken(userDetails)

        return ResponseEntity.ok(SystemUser(jwt).jwt)
    }

    @PutMapping
    fun update(@RequestBody systemUser: SystemUser) = service.update(systemUser)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}