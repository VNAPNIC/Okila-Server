package com.okila.auth.config

import com.okila.common.config.BaseSwagger2Config
import org.springframework.context.annotation.Configuration
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class SwaggerConfig : BaseSwagger2Config()