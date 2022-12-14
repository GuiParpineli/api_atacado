package com.app.atacado.service

import com.app.atacado.exceptions.ResourceNotFoundException
import com.app.atacado.model.SystemUser
import com.app.atacado.model.SystemUserRoles
import com.app.atacado.model.Vendor
import com.app.atacado.model.dto.VendorDTO
import com.app.atacado.repository.SystemUserRepository
import com.app.atacado.repository.VendorRepository
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class VendorService(val repository: VendorRepository, val userRepository: SystemUserRepository, val mapper:ObjectMapper) {

    fun getAll(): ResponseEntity<Any> {
        val vendorList: List<Vendor> = repository.findAll()
        if (vendorList.isEmpty()) ResponseEntity("Nenhum cliente cadastrado", HttpStatus.NOT_FOUND)
        val vendorDTOlist: MutableList<VendorDTO> = mutableListOf()
        vendorList.forEach { v -> vendorDTOlist.add(mapper.convertValue(v)) }
        return ResponseEntity.ok(vendorDTOlist)
    }

    fun get(id: Long): ResponseEntity<Any> {
        val saved: Vendor =
            repository.findById(id).orElseThrow { ResourceNotFoundException("Nenhum Vendedor com o id informado") }
        val vendorDTO: VendorDTO = mapper.convertValue(saved)
        return ResponseEntity.ok(vendorDTO)
    }

    fun save(vendor: Vendor): Vendor {
        userRepository.save(
            SystemUser( null, vendor.name, vendor.lastname, vendor.cpf,
                vendor.password, SystemUserRoles.ROLE_VENDOR )
        )
        return repository.save(vendor)
    }

    fun update(Vendor: Vendor): Vendor { return repository.saveAndFlush(Vendor) }

    fun delete(id: Long) { return repository.deleteById(id) }

}