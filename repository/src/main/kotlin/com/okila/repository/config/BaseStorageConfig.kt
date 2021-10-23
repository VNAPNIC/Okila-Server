package com.okila.repository.config

import com.okila.repository.storage.LocalStorage
import com.okila.repository.storage.Storage
import org.springframework.context.annotation.Bean

open class BaseStorageConfig {
    @Bean
    open fun localStorage(): Storage = LocalStorage()
}