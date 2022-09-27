package com.example.api_atacadokt.service

import com.example.api_atacadokt.model.Product
import com.example.api_atacadokt.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductService(val repository: ProductRepository) {

    fun getAll(): List<Product> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<Product> {
        return repository.findById(id)
    }

    fun save(product: Product): Product {
        return repository.save(product)
    }

    fun update(product: Product): Product {
        return repository.saveAndFlush(product)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}