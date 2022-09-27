package com.example.api_atacadokt.repository

import com.example.api_atacadokt.model.PurchaseOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseOrderRepository : JpaRepository<PurchaseOrder, Long>