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
    fun getAll(): ResponseEntity<Any> {
        val systemUserList: List<SystemUser> = service.getAll()
        if (systemUserList.isEmpty()) return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(systemUserList)
    }

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long): Any {
        val saved: Optional<SystemUser> = service.get(id)
        if (saved.isEmpty) return ResponseEntity("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    @Throws(UserSaveException::class)
    @PostMapping("/cadastrar")
    fun save(@RequestBody systemUser: SystemUser) : ResponseEntity<Any>{
        val user : SystemUser
        try {
            user = service.save(systemUser)
        } catch (e: Exception) {
            throw UserSaveException("Não foi possivel salvar o usuario, tente novamente")
        }
        return ResponseEntity.ok(user)
    }

    @PostMapping("/login")
    @Throws(UserLoginException::class)
    fun login(@RequestBody systemUser: SystemUser): ResponseEntity<Any> {
        try {
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    systemUser.username, systemUser.password
                )
            )
        } catch (e: Exception) {
            throw UserLoginException("Usuario ou senha invalidos")
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