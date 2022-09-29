package com.example.api_atacadokt.service

import com.example.api_atacadokt.model.Customer
import com.example.api_atacadokt.model.SystemUser
import com.example.api_atacadokt.model.SystemUserRoles
import com.example.api_atacadokt.repository.CustomerRepository
import com.example.api_atacadokt.repository.SystemUserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(val repository: CustomerRepository, val userRepository: SystemUserRepository) {

    fun getAll(): List<Customer> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<Customer> {
        return repository.findById(id)
    }

    fun save(customer: Customer): Customer {
        userRepository.save(SystemUser(
            null,
            null,
            customer.razaoSocial,
            customer.nomeFantasia,
            customer.email,
            customer.password,
            SystemUserRoles.ROLE_CUSTOMER
        ))
        return repository.save(customer)
    }

    fun update(customer: Customer): Customer {
        return repository.saveAndFlush(customer)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}