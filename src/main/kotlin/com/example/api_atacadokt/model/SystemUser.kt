package com.example.api_atacadokt.model

import jakarta.persistence.*
import jakarta.validation.constraints.Size
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

@Entity
data class SystemUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var jwt: String = "",
    var name: String = "",
    private var username: String = "",

    @Column(nullable = false, unique = true)
    var email: String = "",

    @Size(min = 4, max = 8)
    private var password: String = "",

    @Enumerated(EnumType.STRING)
    var systemUserRole: SystemUserRoles? = null
) : UserDetails {
    override fun getAuthorities(): MutableSet<SimpleGrantedAuthority> {
        val grantedAuthority: SimpleGrantedAuthority = SimpleGrantedAuthority(systemUserRole!!.name)
        return Collections.singleton(grantedAuthority)
    }

    //
    override fun getPassword(): String {
        return this.password;
    }

    override fun getUsername(): String {
        return this.username
    }

    fun setPassword(value: String) {
        this.password = value
    }

    fun setUsername(value: String) {
        this.username = value
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
