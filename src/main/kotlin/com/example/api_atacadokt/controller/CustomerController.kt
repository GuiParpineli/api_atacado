package com.example.api_atacadokt.controller

import com.example.api_atacadokt.model.Customer
import com.example.api_atacadokt.model.dto.CustomerDTO
import com.example.api_atacadokt.service.CustomerService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/cliente")
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val mapper = ObjectMapper()
        val customerList: List<Customer> = service.getAll()
        if (customerList.isEmpty())
            return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        val customerDTOlist: MutableList<CustomerDTO> = mutableListOf()
        for (c: Customer in customerList) {
            customerDTOlist.add(mapper.convertValue(c))
        }
        return ResponseEntity.ok(customerDTOlist)
    }

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long): Any {
        val saved: Optional<Customer> = service.get(id)
        if (saved.isEmpty)
            return ResponseEntity("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    @PostMapping("/cadastrar")
    fun save(@RequestBody Customer: Customer) = service.save(Customer)

    @PutMapping
    fun update(@RequestBody Customer: Customer) = service.update(Customer)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}