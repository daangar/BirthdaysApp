package com.dgarcia.birthddays.data.repository

import com.dgarcia.birthddays.data.common.coroutine.CoroutineContextProvider
import com.dgarcia.birthddays.data.common.utils.Connectivity
import com.dgarcia.birthddays.data.database.DB_ENTRY_ERROR
import com.dgarcia.birthddays.data.networking.GENERAL_NETWORK_ERROR
import com.dgarcia.birthddays.data.networking.base.DomainMapper
import com.dgarcia.birthddays.domain.model.Result
import com.dgarcia.birthddays.domain.model.Failure
import com.dgarcia.birthddays.domain.model.HttpError
import com.dgarcia.birthddays.domain.model.Success
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {
    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     *  List - Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchListData(
        apiDataProvider: suspend () -> Result<List<T>>,
        dbDataProvider: suspend () -> List<R>
    ): Result<List<T>> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.map {  it.mapToDomainModel() }) else Failure(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }



    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(dataProvider: () -> Result<T>): Result<T> {
        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }
}