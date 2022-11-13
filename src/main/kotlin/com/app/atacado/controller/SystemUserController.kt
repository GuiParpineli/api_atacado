package com.app.atacado.controller

import com.app.atacado.exceptions.UserLoginException
import com.app.atacado.exceptions.UserSaveException
import com.app.atacado.model.SystemUser
import com.app.atacado.security.JwtUtil
import com.app.atacado.service.SystemUserService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.jvm.Throws

@RestController
@RequestMapping("/user", produces = [MediaType.APPLICATION_JSON_VALUE])
class SystemUserController(
    val service: SystemUserService, val authenticationManager: AuthenticationManager, val jwtUtil: JwtUtil
) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long) = service.get(id)

    @PostMapping("/cadastrar")
    fun save(@RequestBody systemUser: SystemUser) = service.save(systemUser)

    @PostMapping("/login")
    @Throws(UserLoginException::class)
    fun login(@RequestBody systemUser: SystemUser): ResponseEntity<Any> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(systemUser.username, systemUser.password)
            )
        } catch (e: Exception) { throw UserLoginException("Usuario ou senha invalidos") }
        val userDetails: UserDetails = service.loadUserByUsername(systemUser.username)
        val jwt: String = jwtUtil.generateToken(userDetails)
        return ResponseEntity.ok(SystemUser(jwt).jwt)
    }

    @PutMapping
    fun update(@RequestBody systemUser: SystemUser) = service.update(systemUser)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}