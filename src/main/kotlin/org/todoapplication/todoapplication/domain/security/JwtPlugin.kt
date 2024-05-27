package org.todoapplication.todoapplication.domain.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.Instant
import java.util.Date


class JwtPlugin {

    companion object {
        const val ISSUER = "Taewan"
        const val SECRET = "qwer123456qwer"
        const val ACCESS_TOKEN_EXPIRES_HOUR: Long = 168
    }

    fun validateToken(token: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
           val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
        }
    }

    fun generateAccessToken(subject: String, userName: String): String{
        return generateToken(subject, userName, Duration.ofHours(ACCESS_TOKEN_EXPIRES_HOUR))
    }

    private fun generateToken(subject: String, userName: String, expirationPeriod: Duration): String{
        val claims: Claims = Jwts.claims()
            .add(mapOf("username" to userName))
            .build()

        val key = Keys.hmacShaKeyFor(SECRET.toByteArray(StandardCharsets.UTF_8))
        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(ISSUER)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(expirationPeriod)))
            .claims(claims)
            .signWith(key)
            .compact()
    }
}