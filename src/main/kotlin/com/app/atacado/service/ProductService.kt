package com.app.atacado.service

import com.app.atacado.model.Product
import com.app.atacado.repository.ProductRepository
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