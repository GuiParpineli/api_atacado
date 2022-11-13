package com.app.atacado.service

import com.app.atacado.exceptions.ResourceNotFoundException
import com.app.atacado.model.Product
import com.app.atacado.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProductService(val repository: ProductRepository) {

    fun getAll(): List<Product> = repository.findAll()

    fun get(id: Long): ResponseEntity<Any> {
        val saved = repository.findById(id).orElseThrow {
            ResourceNotFoundException("Nenhum Produto com id informado")
        }
        return ResponseEntity.ok(saved)
    }

    fun save(product: Product): ResponseEntity<Any> {
        val saved = repository.save(product)
        return ResponseEntity.ok(saved)
    }

    fun update(product: Product): Product {
        return repository.saveAndFlush(product)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}