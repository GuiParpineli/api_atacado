package com.example.api_atacadokt.service

import com.example.api_atacadokt.model.Customer
import com.example.api_atacadokt.repository.CustomerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class CustomerService(val repository: CustomerRepository) {

    fun getAll(): List<Customer> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<Customer> {
        return repository.findById(id)
    }

    fun save(customer: Customer): Customer {
        return repository.save(customer)
    }

    fun update(customer: Customer): Customer {
        return repository.saveAndFlush(customer)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}