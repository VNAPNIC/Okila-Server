package com.okila.auth.service

import com.okila.auth.repository.AccountRepository
import com.okila.auth.repository.DeviceRepository
import com.okila.auth.repository.UserRepository
import com.okila.dto.AccountResponse
import com.okila.repository.entities.AccountEntity
import com.okila.repository.entities.DeviceEntity
import com.okila.repository.entities.UserEntity
import com.okila.repository.enums.Platform
import com.okila.repository.enums.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList

interface AuthService {
    fun existsByPhoneNumber(phoneNumber: String): Boolean
    fun saveAccount(staffId: String? = null,
                    phoneNumber: String? = null,
                    socialId: String? = null,
                    email: String? = null,
                    password: String? = null,
                    role: Role? = null,
                    deviceId: String? = null,
                    deviceName: String? = null,
                    platform: Platform = Platform.OTHER): AccountResponse?

    fun saveDeviceInfo(device: DeviceEntity): DeviceEntity?
}

@Service
class AuthServiceImpl : AuthService {
    @Autowired
    lateinit var accountRepository: AccountRepository

    @Autowired
    lateinit var deviceRepository: DeviceRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var passwordEncoder: PasswordEncoder

    override fun existsByPhoneNumber(phoneNumber: String): Boolean = accountRepository.existsByPhoneNumber(phoneNumber)

    override fun saveAccount(staffId: String?, phoneNumber: String?, socialId: String?, email: String?, password: String?, role: Role?, deviceId: String?, deviceName: String?, platform: Platform): AccountResponse? {
        // create and save device
        val devices = saveDeviceInfo(deviceId = deviceId,
                deviceName = deviceName,
                platform = platform)

        // make userInfo default
        val user = userRepository.insert(UserEntity())

        // insert to database
        val result = accountRepository.insert(AccountEntity(
                socialId = socialId,
                email = email,
                phoneNumber = phoneNumber,
                password = if (password.isNullOrEmpty()) null else encryptPassword(password),
                registerTime = Date.from(Instant.now()),
                collaboratorId = staffId,
                devices = devices,
                role = role,
                info = user
        ))

        // return dto
        return AccountResponse.from(result)
    }

    override fun saveDeviceInfo(device: DeviceEntity): DeviceEntity? = deviceRepository.save(device)

    private fun saveDeviceInfo(deviceId: String?, deviceName: String?, platform: Platform): ArrayList<DeviceEntity?> {
        // create and save device
        val devices = arrayListOf<DeviceEntity?>()

        // save to database
        val device = saveDeviceInfo(DeviceEntity(
                deviceId = deviceId,
                deviceName = deviceName,
                platform = platform))

        devices.add(device)
        return devices
    }

    private fun encryptPassword(password: String?): String? = passwordEncoder.encode(password)
}