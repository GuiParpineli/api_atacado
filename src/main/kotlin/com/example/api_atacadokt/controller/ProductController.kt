package com.example.kotlinapi.controller

import com.example.kotlinapi.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produtos")
class ProductController(val service: ProductService) {

    @GetMapping("/todos")
    fun getAll() = service.getAll()

}