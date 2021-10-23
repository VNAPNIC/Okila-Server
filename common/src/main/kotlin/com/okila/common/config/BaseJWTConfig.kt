package com.okila.common.config

import com.okila.common.service.JWTService
import com.okila.common.service.JWTServiceImpl
import org.springframework.context.annotation.Bean

open class BaseJWTConfig {
    @Bean
    open fun jwtService(): JWTService = JWTServiceImpl()
}