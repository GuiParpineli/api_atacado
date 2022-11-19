package com.app.atacado.controller

import com.app.atacado.service.UserService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("user", produces = [MediaType.APPLICATION_JSON_VALUE])
class UserController (val service: UserService){

    @GetMapping
    fun getAll() = service.getAll()
}