package com.okila.auth.controller

import com.okila.common.service.JWTService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-out")
class SignOutController {
    @Autowired
    lateinit var jwtService: JWTService
}