package com.app.atacado.service

import com.app.atacado.model.Customer
import com.app.atacado.model.SystemUser
import com.app.atacado.model.SystemUserRoles
import com.app.atacado.repository.CustomerRepository
import com.app.atacado.repository.SystemUserRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class CustomerService(val repository: CustomerRepository, val userRepository: SystemUserRepository) {

    fun getAll(): List<Customer> = repository.findAll()

    fun get(id: Long): Optional<Customer> = repository.findById(id)

    fun save(customer: Customer): Customer {
        userRepository.save(
            SystemUser(
                null,
                customer.razaoSocial,
                customer.nomeFantasia,
                customer.email,
                customer.password,
                SystemUserRoles.ROLE_CUSTOMER
            )
        )
        return repository.save(customer)
    }

    fun update(customer: Customer): Customer = repository.saveAndFlush(customer)

    fun delete(id: Long) = repository.deleteById(id)

}