package com.example.api_atacadokt.service

import com.example.api_atacadokt.model.PurchaseOrder
import com.example.api_atacadokt.repository.PurchaseOrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PurchaseOrderService(val repository: PurchaseOrderRepository) {

    fun getAll(): List<PurchaseOrder> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<PurchaseOrder> {
        return repository.findById(id)
    }

    fun save(PurchaseOrder: PurchaseOrder): PurchaseOrder {
        return repository.save(PurchaseOrder)
    }

    fun update(PurchaseOrder: PurchaseOrder): PurchaseOrder {
        return repository.saveAndFlush(PurchaseOrder)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}