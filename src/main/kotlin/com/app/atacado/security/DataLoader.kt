package com.app.atacado.security;

import com.app.atacado.model.SystemUser
import com.app.atacado.model.SystemUserRoles
import com.app.atacado.repository.SystemUserRepository
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class DataLoader(val userRepository: SystemUserRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val bCryptPasswordEncoder = BCryptPasswordEncoder();
        val userAdmin = SystemUser(
            1,  "admin", "admin", "admin@email.com",
            bCryptPasswordEncoder.encode("admin"),
            SystemUserRoles.ROLE_ADMIN
        );
        userRepository.save(userAdmin);
    }
}
