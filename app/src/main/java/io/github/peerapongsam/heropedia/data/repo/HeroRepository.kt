package io.github.peerapongsam.heropedia.data.repo

import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.Observable

interface HeroRepository {

    fun getHeroes(): Observable<List<Hero>>

    fun getHeroDetail(id: String): Observable<HeroDetail>
}
