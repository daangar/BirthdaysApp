package com.dgarcia.birthddays.data.networking.base

import com.dgarcia.birthddays.data.database.DB_ENTRY_ERROR
import com.dgarcia.birthddays.data.networking.GENERAL_NETWORK_ERROR
import com.dgarcia.birthddays.domain.model.Failure
import com.dgarcia.birthddays.domain.model.HttpError
import com.dgarcia.birthddays.domain.model.Result
import com.dgarcia.birthddays.domain.model.Success
import retrofit2.Response
import java.io.IOException

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<List<R>>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (List<R>) -> Unit,
    fetchFromCacheAction: () -> List<R>): Result<List<U>> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.map { entity -> entity.mapToDomainModel() })
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) Success(cachedModel.map { it.mapToDomainModel() }) else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
        }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}



/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    try {
        onSuccess { return Success(it.mapToDomainModel()) }
        onFailure { return Failure(it) }
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
    }
}
