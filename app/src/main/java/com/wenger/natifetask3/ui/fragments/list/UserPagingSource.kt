package com.wenger.natifetask3.ui.fragments.list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.wenger.natifetask3.api.ApiService
import com.wenger.natifetask3.data.ResultResponse
import okio.IOException
import retrofit2.HttpException
import java.lang.IllegalArgumentException

class UserPagingSource(
    private val api: ApiService
) : PagingSource<Int, ResultResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ResultResponse>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResultResponse> {
        val pageIndex = params.key ?: 1
        return try {
            val response = api.getUsers()
            val users = response.body()?.results
            if (users != null) {
                val nextKey =
                    if (users.isEmpty()) {
                        null
                    } else {
                        pageIndex + (params.loadSize / 20)
                    }
                LoadResult.Page(
                    data = users,
                    prevKey = if (pageIndex == 1) null else pageIndex,
                    nextKey = nextKey
                )
            } else {
                return LoadResult.Error(IllegalArgumentException("No users"))
            }
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}