package com.okila.dto

import com.okila.repository.enums.Platform

data class SignUpRequest(
        val code: String?,
        // Phone
        val phoneNumber: String?,
        val dialCode: String?,
        val numCode: String?,
        val alpha2Code: String?,
        val alpha3Code: String?,
        val enShortName: String?,
        val nationality: String?,
        // info
        val socialId: String?,
        val email: String?,
        val password: String?,
        val deviceName: String?,
        val deviceId: String?,
        val platform: Platform
)