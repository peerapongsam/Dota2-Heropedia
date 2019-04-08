package io.github.peerapongsam.heropedia.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Service {

    const val BASE_URL = "https://peerapongsam.github.io/heropedia/json/"

    val defaultClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(Level.BODY))
            .build()
    }

    val defaultService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(defaultClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
