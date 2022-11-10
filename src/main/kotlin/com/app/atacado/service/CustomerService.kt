package com.app.atacado.service

import com.app.atacado.exceptions.ResourceNotFoundException
import com.app.atacado.model.Customer
import com.app.atacado.model.SystemUser
import com.app.atacado.model.SystemUserRoles
import com.app.atacado.model.dto.CustomerDTO
import com.app.atacado.repository.CustomerRepository
import com.app.atacado.repository.SystemUserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val repository: CustomerRepository,
    val userRepository: SystemUserRepository,
    val mapper: ObjectMapper
) {

    fun getAll(): ResponseEntity<Any> {
        val customerList: List<Customer> = repository.findAll()
        if (customerList.isEmpty()) ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        val customerDTO: MutableList<CustomerDTO> = mutableListOf()
        customerList.forEach { c ->  customerDTO.add(mapper.convertValue(c))}
        return ResponseEntity.ok(customerDTO)
    }

    fun get(id: Long): ResponseEntity<Any> {
        val saved: Customer = repository.findById(id)
            .orElseThrow { ResourceNotFoundException("Nenhum cliente com o id informado") }
        val customerDtO: CustomerDTO = mapper.convertValue(saved)
        return ResponseEntity.ok(customerDtO)
    }

    fun save(customer: Customer): ResponseEntity<Any> {
        val saved = repository.save(customer)
        userRepository.save(
            SystemUser( null, customer.razaoSocial, customer.nomeFantasia, customer.email,
                customer.password, SystemUserRoles.ROLE_CUSTOMER )
        )
        return ResponseEntity.ok(saved)
    }

    fun update(customer: Customer): Customer = repository.saveAndFlush(customer)

    fun delete(id: Long) = repository.deleteById(id)

}