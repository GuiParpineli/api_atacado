package com.app.atacado.controller

import com.app.atacado.model.Customer
import com.app.atacado.model.dto.CustomerDTO
import com.app.atacado.service.CustomerService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cliente", produces = [MediaType.APPLICATION_JSON_VALUE])
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long) = service.get(id)

    @PostMapping("/cadastrar")
    fun save(@RequestBody customer: Customer) = service.save(customer)

    @PutMapping
    fun update(@RequestBody customer: Customer) = service.update(customer)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}