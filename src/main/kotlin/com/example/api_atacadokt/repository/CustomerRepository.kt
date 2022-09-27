package com.example.api_atacadokt.repository

import com.example.api_atacadokt.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
interface CustomerRepository : JpaRepository<Customer, Long> {
}