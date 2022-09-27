package com.example.api_atacadokt.security;

import com.example.api_atacadokt.model.SystemUser;
import com.example.api_atacadokt.model.SystemUserRoles;
import com.example.api_atacadokt.repository.SystemUserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
class DataLoader(val userRepository: SystemUserRepository) : ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        val bCryptPasswordEncoder = BCryptPasswordEncoder();
        val userAdmin = SystemUser(
            1, null, "admin", "admin", "admin@email.com", bCryptPasswordEncoder.encode(
                "admin"
            ), SystemUserRoles.ROLE_ADMIN
        );
        userRepository.save(userAdmin);
    }
}
