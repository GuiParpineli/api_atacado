package com.app.atacado.service

import com.app.atacado.model.SystemUser
import com.app.atacado.model.SystemUserRoles
import com.app.atacado.model.Vendor
import com.app.atacado.repository.SystemUserRepository
import com.app.atacado.repository.VendorRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VendorService(val repository: VendorRepository, val userRepository: SystemUserRepository) {

    fun getAll(): List<Vendor> {
        return repository.findAll()
    }

    fun get(id: Long): Optional<Vendor> {
        return repository.findById(id)
    }

    fun save(vendor: Vendor): Vendor {
        userRepository.save(
            SystemUser(
                null,
                vendor.name,
                vendor.lastname,
                vendor.cpf,
                vendor.password,
                SystemUserRoles.ROLE_VENDOR
            )
        )
        return repository.save(vendor)
    }

    fun update(Vendor: Vendor): Vendor {
        return repository.saveAndFlush(Vendor)
    }

    fun delete(id: Long) {
        return repository.deleteById(id)
    }

}