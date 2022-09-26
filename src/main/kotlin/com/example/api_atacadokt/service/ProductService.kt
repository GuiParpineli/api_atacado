package com.example.api_atacadokt.service

import com.example.api_atacadokt.model.Product
import com.example.api_atacadokt.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val repository: ProductRepository) {

    fun getAll(): List<Product> {
        return repository.findAll()
    }

    fun get(id: Long): Product {
        return repository.getReferenceById(id)
    }

    fun save(product: Product): Product {
        return repository.save(product)
    }

}