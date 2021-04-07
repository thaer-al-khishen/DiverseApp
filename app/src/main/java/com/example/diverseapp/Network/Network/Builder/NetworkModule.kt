package com.example.diverseapp.Network.Network.Builder

import com.example.diverseapp.Network.Network.Services.CardsAPIService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//Not included in the project
private const val MODULE_NAME = "Network Module"

val networkModule = Kodein.Module(MODULE_NAME, false) {
    bind<OkHttpClient>() with singleton { getMockOkHttpClient() }
    bind<Retrofit>() with singleton {
        getMockRetrofit(
            instance()
        )
    }
    bind<CardsAPIService>() with singleton {
        getMockApiService(
            instance()
        )
    }
}

private fun getMockOkHttpClient(): OkHttpClient {

    val httpBuilder = OkHttpClient.Builder()
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100,TimeUnit.SECONDS)
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    httpBuilder.interceptors()
        .add(httpLoggingInterceptor)

    return httpBuilder.build()
}

private fun getMockRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(UrlBuilder.BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .client(okHttpClient)
    .build()

private fun getMockApiService(retrofit: Retrofit): CardsAPIService =
    retrofit.create(CardsAPIService::class.java)