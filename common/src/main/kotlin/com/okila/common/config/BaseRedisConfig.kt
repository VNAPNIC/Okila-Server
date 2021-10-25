package com.okila.common.config

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import com.okila.common.service.RedisService
import com.okila.common.service.RedisServiceImpl
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.context.annotation.Bean
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer

open class BaseRedisConfig : CachingConfigurerSupport() {

    @Bean
    open fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val stringRedisSerializer = StringRedisSerializer()
        val serializer = Jackson2JsonRedisSerializer(Any::class.java)
        val objectMapper = ObjectMapper()
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL)
        serializer.setObjectMapper(objectMapper)
        return RedisTemplate<String, Any>().apply {
            this.setConnectionFactory(factory)
            this.keySerializer = stringRedisSerializer
            this.hashKeySerializer = stringRedisSerializer
            this.valueSerializer = serializer
            this.hashValueSerializer = serializer
            this.afterPropertiesSet()
        }
    }

    @Bean
    open fun redisService(redisTemplate: RedisTemplate<String, Any>): RedisService = RedisServiceImpl(redisTemplate)
}