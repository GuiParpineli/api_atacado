package com.example.kotlinapi.service

import com.example.api_atacadokt.model.Product
import com.example.kotlinapi.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(val repository: ProductRepository) {

    fun getAll(): List<Product> {
        return repository.findAll();
    }

}