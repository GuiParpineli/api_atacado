package com.app.atacado.model

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import javax.persistence.*
import javax.validation.constraints.Size


@Entity
data class SystemUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String = "",
    private var username: String = "",
    @Column(nullable = false, unique = true)
    var email: String = "",
    @Size(min = 3, max = 8)
    private var password: String = "",

    @Enumerated(EnumType.STRING)
    var systemUserRole: SystemUserRoles? = null,

) : UserDetails {

    var jwt: String? = ""
    constructor(jwt: String) : this(){
        this.jwt = jwt
    }

    override fun getAuthorities(): MutableSet<SimpleGrantedAuthority> {
        val grantedAuthority = SimpleGrantedAuthority(systemUserRole!!.name)
        return Collections.singleton(grantedAuthority)
    }

    //
    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.username
    }

    //
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    //
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    //
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    //
    override fun isEnabled(): Boolean {
        return true
    }
}
