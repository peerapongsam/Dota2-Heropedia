package io.github.peerapongsam.heropedia.data.remote

import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HeropediaService {

    @GET("{language}/heroes.json")
    fun getHeroes(@Path("language") language: String = "thai"): Observable<List<Hero>>

    @GET("{language}/hero/{name}.json")
    fun getHeroDetail(
        @Path("language") language: String = "thai",
        @Path("name") name: String
    ): Observable<HeroDetail>
}
