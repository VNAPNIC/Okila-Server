package com.okila.auth.config

import com.okila.common.config.BaseRedisConfig
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
class RedisConfig : BaseRedisConfig()