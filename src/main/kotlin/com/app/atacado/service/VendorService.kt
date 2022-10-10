package com.app.atacado.service

import com.app.atacado.model.Vendor
import com.app.atacado.repository.VendorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VendorService(val repository: VendorRepository) {

    fun getAll(): List<Vendor> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<Vendor> {
        return repository.findById(id)
    }

    fun save(Vendor: Vendor): Vendor {
        return repository.save(Vendor)
    }

    fun update(Vendor: Vendor): Vendor {
        return repository.saveAndFlush(Vendor)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}