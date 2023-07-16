package com.azalia.suitmediatestkm.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.azalia.suitmediatestkm.data.remote.ApiService
import com.azalia.suitmediatestkm.data.response.User
import java.lang.Exception

class UserPagingSource(private val apiService: ApiService) : PagingSource<Int, User>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { a ->
            val anchorPage = state.closestPageToPosition(anchorPosition = a)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.getUsers(page, params.loadSize)
            LoadResult.Page(
                data = responseData.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.data.isNullOrEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

}