package com.okila.auth.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

interface OtpService {

}

@Service
class  OtpServiceImpl : OtpService{
    private val log = LoggerFactory.getLogger(OtpService::class.java)
}