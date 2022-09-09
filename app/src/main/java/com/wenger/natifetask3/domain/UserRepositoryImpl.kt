package com.wenger.natifetask3.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wenger.natifetask3.api.ApiService
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.data.managers.DataManager
import com.wenger.natifetask3.ui.fragments.list.UserPagingSource
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(
    private val api: ApiService,
    private val dataManager: DataManager
) : UserRepository {

    override fun getAllUsers(): Flow<PagingData<ResultResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                UserPagingSource(api)
            }
        ).flow
    }

    override suspend fun getUserById(uuid: String): User {
        return dataManager.getUserById(uuid)
    }

}