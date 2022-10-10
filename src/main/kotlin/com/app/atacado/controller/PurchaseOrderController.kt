package com.app.atacado.controller

import com.app.atacado.model.PurchaseOrder
import com.app.atacado.service.PurchaseOrderService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/pedido")
class PurchaseOrderController(val service: PurchaseOrderService) {
    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val purchaseOrderList: List<PurchaseOrder> = service.getAll()
        if (purchaseOrderList.isEmpty())
            return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(purchaseOrderList)
    }

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long): Any {
        val saved: Optional<PurchaseOrder> = service.get(id)
        if (saved.isEmpty)
            return ResponseEntity("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    @PostMapping("/cadastrar")
    fun save(@RequestBody PurchaseOrder: PurchaseOrder) = service.save(PurchaseOrder)

    @PutMapping
    fun update(@RequestBody PurchaseOrder: PurchaseOrder) = service.update(PurchaseOrder)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}