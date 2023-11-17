package org.sopt.dosopttemplate.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.sopt.dosopttemplate.BuildConfig
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReqresModule {
    private const val REQRES_URL = BuildConfig.REQRES_URL
    private const val CONTENT_TYPE = "application/json"

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ReqresType

    @Provides
    @Singleton
    @ReqresType
    fun provideReqresOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    @ReqresType
    fun provideReqresRetrofit(@ReqresType okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(REQRES_URL)
        .addConverterFactory(Json.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()
}
