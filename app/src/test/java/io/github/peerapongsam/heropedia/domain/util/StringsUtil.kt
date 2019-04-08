package io.github.peerapongsam.heropedia.domain.util

import java.util.UUID

object StringsUtil {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }
}
