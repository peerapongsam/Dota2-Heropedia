package io.github.peerapongsam.heropedia.data.repo

import io.github.peerapongsam.heropedia.data.remote.HeropediaService
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.reactivex.Observable

class HeroRepositoryImpl(private val service: HeropediaService) : HeroRepository {

    override fun getHeroes(): Observable<List<Hero>> {
        return service.getHeroes()
    }

    override fun getHeroDetail(id: String): Observable<HeroDetail> {
        return service.getHeroDetail(name = id)
    }
}
