package com.app.atacado.controller

import com.app.atacado.model.Product
import com.app.atacado.service.ProductService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.Optional

@RestController
@RequestMapping("/produto", produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(val service: ProductService) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long) = service.get(id)

    @PostMapping("/cadastrar")
    fun save(@RequestBody product: Product) = service.save(product)

    @PutMapping
    fun update(@RequestBody product: Product) = service.update(product)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}