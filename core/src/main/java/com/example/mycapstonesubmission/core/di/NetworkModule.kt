package com.example.mycapstonesubmission.core.di

import com.example.mycapstonesubmission.core.data.source.remote.network.ApiService
import com.squareup.leakcanary.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val token ="eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjNWNhMWViMDM4NWI0ZGI3YjYwYjE5YWY4ZTQyZmUzZSIsIm5iZiI6MTc1MTU1MTM1Ny41NjQ5OTk4LCJzdWIiOiI2ODY2OGQ3ZDQ0NmJhMGY3NjU5YzJmMDgiLCJzY29wZXMiOlsiYXBpX3JlYWQiXSwidmVyc2lvbiI6MX0.cZmPcBX2c6uzymTbWsU4AlFfOooTF0aDgtHSSCVfvlM"
        val loggingInterceptor =
            if(BuildConfig.DEBUG) { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }else { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE) }
        val authInterceptor= Interceptor{chain ->
            val req = chain.request()
            val requestHeaders = req.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(requestHeaders)
        }
        val certificatePinner = CertificatePinner.Builder()
            .add("api.themoviedb.org", "sha256/f78NVAesYtdZ9OGSbK7VtGQkSIVykh3DnduuLIJHMu4=")
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

}