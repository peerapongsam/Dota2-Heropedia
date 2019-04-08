package io.github.peerapongsam.heropedia.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceUtil {

    private const val BASE_URL = "https://peerapongsam.github.io/heropedia/json/"

    private val defaultClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()
    }

    private val defaultService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(defaultClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val heropediaService by lazy {
        defaultService.create(HeropediaService::class.java)
    }
}
