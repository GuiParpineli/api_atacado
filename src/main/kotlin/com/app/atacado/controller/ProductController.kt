package com.app.atacado.controller

import com.app.atacado.model.Product
import com.app.atacado.service.ProductService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

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