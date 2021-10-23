package com.okila.auth.controller

import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.okila.common.service.JWTService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/verify")
class VerifyController {
    private val log = LoggerFactory.getLogger(SignUpController::class.java)

    val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()

    @Autowired
    lateinit var jwtService: JWTService
}