package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.repository.AuthRepositoryImpl
import org.sopt.dosopttemplate.data.repository.ReqresRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindToAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindToReqresRepository(
        reqresRepositoryImpl: ReqresRepositoryImpl
    ): ReqresRepository
}