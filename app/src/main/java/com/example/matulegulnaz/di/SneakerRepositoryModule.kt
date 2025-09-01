//package com.example.matulegulnaz.di
//
//import com.example.matulegulnaz.data.product.LocalSneakerRepository
//import com.example.matulegulnaz.data.product.RemoteSneakerRepository
//import com.example.matulegulnaz.data.product.SneakerRepositoryImpl
//import com.example.matulegulnaz.domain.product.SneakerRepository
//import dagger.Binds
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import dagger.multibindings.IntoSet
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class SneakerRepositoryModule {
//    @Binds
//    @IntoSet
//    abstract fun provideLocalSneakerRepository(localSneakerRepository: LocalSneakerRepository)
//            : SneakerRepository
//
//    @Binds
//    @IntoSet
//    abstract fun provideRemoteSneakerRepository(remoteSneakerRepository: RemoteSneakerRepository)
//            : SneakerRepository
//
//    @Binds
//    @IntoSet
//    abstract fun provideSneakerRepository(sneakerRepositoryImpl: SneakerRepositoryImpl)
//            : SneakerRepository
//}