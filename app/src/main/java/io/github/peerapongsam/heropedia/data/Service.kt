package io.github.peerapongsam.heropedia.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object Service {
    val defaultClient by lazy {
        OkHttpClient.Builder()
            .build()
    }

    val defaultService by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.dota2.com/jsfeed/")
            .client(defaultClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
