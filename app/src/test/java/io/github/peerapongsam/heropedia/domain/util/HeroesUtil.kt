package io.github.peerapongsam.heropedia.domain.util

import io.github.peerapongsam.heropedia.domain.util.StringsUtil.randomString
import io.github.peerapongsam.heropedia.model.Hero
import io.github.peerapongsam.heropedia.model.HeroDetail
import io.github.peerapongsam.heropedia.model.Portrait

object HeroesUtil {

    fun makeHeroes(count: Int): List<Hero> {
        val heroes = mutableListOf<Hero>()
        repeat(count) {
            heroes.add(makeHero())
        }
        return heroes
    }

    private fun makeHero(): Hero {
        return Hero(
            id = randomString(),
            name = randomString(),
            primaryAttribs = randomString(),
            dac = randomString(),
            roles = listOf(randomString()),
            portrait = Portrait(full = randomString(), vert = randomString())
        )
    }

    fun makeHeroDetail(): HeroDetail {
        return HeroDetail(
            id = randomString(),
            name = randomString(),
            attack = randomString(),
            roles = listOf(randomString(), randomString(), randomString()),
            overview = null
        )
    }
}
