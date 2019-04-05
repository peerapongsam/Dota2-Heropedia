package io.github.peerapongsam.heropedia.domain.usecase.base

interface UseCase<Result, Params> {

    fun execute(params: Params? = null): Result
}
