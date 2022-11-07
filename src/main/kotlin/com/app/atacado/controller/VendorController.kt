package com.app.atacado.controller

import com.app.atacado.model.Vendor
import com.app.atacado.model.dto.VendorDTO
import com.app.atacado.service.VendorService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/vendedor", produces = [MediaType.APPLICATION_JSON_VALUE])
class VendorController(val service: VendorService) {

    @GetMapping
    fun getAll() = service.getAll()

    @GetMapping("/buscarId")
    fun get(@RequestParam("id") id: Long) = service.get(id)

    @PostMapping("/cadastrar")
    fun save(@RequestBody Vendor: Vendor) = service.save(Vendor)

    @PutMapping
    fun update(@RequestBody Vendor: Vendor) = service.update(Vendor)

    @DeleteMapping
    fun delete(@RequestParam("id") id: Long) = service.delete(id)
}