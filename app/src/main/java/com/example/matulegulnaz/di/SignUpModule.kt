package com.example.matulegulnaz.di

import com.example.matulegulnaz.data.authorization.AuthResult
import com.example.matulegulnaz.presentation.authorization.common.AuthResultMapper
import com.example.matulegulnaz.presentation.authorization.common.AuthUiResultState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SignUpModule {

    @Binds
    abstract fun provideMapper(
        authResultMapper: AuthResultMapper
    ) : AuthResult.Mapper<AuthUiResultState>
}