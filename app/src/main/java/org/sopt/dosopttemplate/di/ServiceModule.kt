package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.service.AuthService
import org.sopt.dosopttemplate.data.service.ReqresService
import org.sopt.dosopttemplate.di.RetrofitModule.SoptType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideReqresService(@ReqresModule.ReqresType retrofit: Retrofit): ReqresService =
        retrofit.create(ReqresService::class.java)

    @Provides
    @Singleton
    fun provideAuthService(@SoptType retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)
}