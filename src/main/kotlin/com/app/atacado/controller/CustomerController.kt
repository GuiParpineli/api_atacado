package com.app.atacado.controller

import com.app.atacado.model.Customer
import com.app.atacado.model.dto.CustomerDTO
import com.app.atacado.service.CustomerService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cliente")
class CustomerController(val service: CustomerService) {

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val mapper = ObjectMapper()
        val customerList: List<Customer> = service.getAll()
        if (customerList.isEmpty())
            return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        val customerDTO: MutableList<CustomerDTO> = mutableListOf()
        for (c: Customer in customerList)
            customerDTO.add(mapper.convertValue(c))
        return ResponseEntity.ok(customerDTO)
    }

}