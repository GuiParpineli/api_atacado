package com.example.api_atacadokt.model

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
class SystemUser : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    var jwt: String = ""
    var name: String = ""

    @Column(nullable = false, unique = true)
    var email: String = ""

    @Size(min = 4, max = 8)
    private var password: String = ""

    @Enumerated(EnumType.STRING)
    @NotNull
    var systemUserRole: SystemUserRoles? = null

    override fun getAuthorities(): MutableSet<SimpleGrantedAuthority> {
        val grantedAuthority: SimpleGrantedAuthority = SimpleGrantedAuthority(systemUserRole!!.name)
        return Collections.singleton(grantedAuthority)
    }

    //
    override fun getPassword(): String {
        return this.password;
    }

    //
    override fun getUsername(): String {
        return this.username
    }

    //
    override fun isAccountNonExpired(): Boolean {
        return true;
    }

    //
    override fun isAccountNonLocked(): Boolean {
        return true;
    }

    //
    override fun isCredentialsNonExpired(): Boolean {
        return true;
    }

    //
    override fun isEnabled(): Boolean {
        return true;
    }
}
