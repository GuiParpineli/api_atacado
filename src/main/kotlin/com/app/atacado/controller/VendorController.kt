package com.app.atacado.controller

import com.app.atacado.model.Vendor
import com.app.atacado.model.dto.VendorDTO
import com.app.atacado.service.VendorService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/vendedor")
class VendorController(val service: VendorService) {

    @GetMapping
    fun getAll(): ResponseEntity<Any> {
        val mapper = ObjectMapper()
        val vendorList: List<Vendor> = service.getAll()
        if (vendorList.isEmpty())
            return ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        val vendorDTOlist: MutableList<VendorDTO> = mutableListOf()
        for (c: Vendor in vendorList) {
            vendorDTOlist.add(mapper.convertValue(c))
        }
        return ResponseEntity.ok(vendorDTOlist)
    }

    @GetMapping("buscarId")
    fun get(@RequestParam("id") id: Long): Any {
        val saved: Optional<Vendor> = service.get(id)
        if (saved.isEmpty)
            return ResponseEntity("Nenhum Usuario encontrado", HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(saved)
    }

    @PostMapping("/cadastrar")
    fun save(@RequestBody Vendor: Vendor) = service.save(Vendor)

    @PutMapping
    fun update(@RequestBody Vendor: Vendor) = service.update(Vendor)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}