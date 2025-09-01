package com.example.matulegulnaz.data.user

import com.example.matulegulnaz.data.database.remote.dto.RemoteUserDto
import com.example.matulegulnaz.domain.user.User
import io.github.jan.supabase.auth.user.UserInfo

abstract class Mapper {

    protected fun RemoteUserDto.toUser(): User{
        return User(
            name = name,
            img = img
        )
    }


}