package com.app.atacado.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import kotlin.collections.HashMap
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Component
class JwtUtil {
    val secretKey = "secrhdfjhsdfoiuoadflsdagasdlkslkdklsaaaaaaaaaaaaaaaaaaat"

    fun extractUserName(token: String): String = extractClaimUsername(token)

    fun extractExpiration(token: String): Date = extractClaimDate(token)

    fun extractClaimDate(token: String): Date {
        val claims: Claims = extractAllClaims(token)
        return claims.expiration
    }

    fun extractClaimUsername(token: String): String {
        val claims: Claims = extractAllClaims(token)
        return claims.subject
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .body
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims: Map<String, JvmType.Object> = HashMap()
        return createToken(claims, userDetails.username)
    }

    fun createToken(claims: Map<String, JvmType.Object>, subject: String): String {
        return Jwts.builder()
            .setIssuer("quarkbyte")
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 8640000))
            .signWith(SignatureAlgorithm.HS256, secretKey).compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username: String = extractUserName(token)
        return (username == userDetails.username && !isTokenExpired(token))
    }

    fun isTokenExpired(token: String): Boolean {
        return extractExpiration(token).before(Date())
    }

}