package com.example.matulegulnaz.di

import com.example.matulegulnaz.base.navigation.NavigationRepository
import com.example.matulegulnaz.data.onboarding.NavigationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class OnBoardingModule {
    @Binds
    @Singleton
    abstract fun provideOnboardingRepository(
        navigationRepositoryImpl: NavigationRepositoryImpl
    ): NavigationRepository
}