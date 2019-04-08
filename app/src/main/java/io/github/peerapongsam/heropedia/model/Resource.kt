package io.github.peerapongsam.heropedia.model

data class Resource<T>(
    val status: Status,
    val data: T? = null,
    val throwable: Throwable? = null
) {

    companion object {
        fun <T> success(data: T?) = Resource<T>(Status.SUCCESS, data)

        fun <T> error(throwable: Throwable?) = Resource<T>(Status.ERROR, null, throwable)

        fun <T> loading() = Resource<T>(Status.LOADING, null, null)
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
