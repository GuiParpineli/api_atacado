package com.example.api_atacadokt

import com.example.api_atacadokt.model.Product
import com.example.api_atacadokt.repository.ProductRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component


@Component
class DataLoader(val productRepository: ProductRepository) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        var product = Product(1, "Bola", "123131", "Brinquedos", 12, 2.99, 9.90)
        var product2 = Product(2, "Boneca", "123131", "Brinquedos", 222, 4.99, 19.90)
        var product3 = Product(3, "Piao", "123131", "Brinquedos", 4442, 3.99, 5.90)
        var product4 = Product(4, "Faca", "123131", "Casa", 552, 0.99, 2.90)

        productRepository.save(product)
        productRepository.save(product2)
        productRepository.save(product3)
        productRepository.save(product4)
    }
}