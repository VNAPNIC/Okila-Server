package com.okila.common.property

data class JwtProperty(
        val jwtTTL: Long = 7776000000, //90 days (ms)
        val jwtPhase: String = "9b6dae5aeedc5432dd25f160e829b64e",
        val jwtIssuer: String = "cleaner"
)
