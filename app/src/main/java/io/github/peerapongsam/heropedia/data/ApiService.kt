package io.github.peerapongsam.heropedia.data

import io.github.peerapongsam.heropedia.model.HeroData
import io.github.peerapongsam.heropedia.model.HeropediaData
import io.reactivex.Observable
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("heropickerdata")
    fun getHeroPickData(): Observable<Map<String, HeroData>>

    @GET("heropediadata")
    fun getHeropediaData(
        @Query("feeds") feeds: String = "herodata",
        @Query("l") language: String = "thai"
    ): Observable<HeropediaData>
}
