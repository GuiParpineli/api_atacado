package com.example.api_atacadokt

import com.example.api_atacadokt.model.Product
import com.example.api_atacadokt.model.SystemUser
import com.example.api_atacadokt.model.SystemUserRoles
import com.example.api_atacadokt.repository.ProductRepository
import com.example.api_atacadokt.repository.SystemUserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component


@Component
class DataLoader(val productRepository: ProductRepository,val userRepository: SystemUserRepository,) :  ApplicationRunner {

    val bCryptPasswordEncoder: BCryptPasswordEncoder = BCryptPasswordEncoder()
    override fun run(args: ApplicationArguments?) {
        val product = Product(1, "Bola", "123131", "Brinquedos", 12, 2.99, 9.90)

        val product2 = Product(2, "Boneca", "123131", "Brinquedos", 222, 4.99, 19.90)

        val product3 = Product(3, "Piao", "123131", "Brinquedos", 4442, 3.99, 5.90)

        val product4 = Product(4, "Faca", "123131", "Casa", 552, 0.99, 2.90)


        val userCustomer = SystemUser(
            1, "", "customer", "customer", "customer@email.com", bCryptPasswordEncoder
                .encode("customer"), SystemUserRoles.ROLE_CUSTOMER)
        val userVendor = SystemUser(
            2, "", "vendor", "vendor", "vendor@email.com", bCryptPasswordEncoder
                .encode("vendor"), SystemUserRoles.ROLE_VENDOR)
        val userAdmin = SystemUser(
            3, "", "admin", "admin", "admin@email.com", bCryptPasswordEncoder
                .encode("admin"), SystemUserRoles.ROLE_ADMIN)

        productRepository.save(product)
        productRepository.save(product2)
        productRepository.save(product3)
        productRepository.save(product4)

        userRepository.save(userCustomer);
        userRepository.save(userVendor);
        userRepository.save(userAdmin);

    }
}