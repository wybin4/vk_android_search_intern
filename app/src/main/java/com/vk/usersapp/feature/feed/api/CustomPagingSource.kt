package com.vk.usersapp.feature.feed.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vk.usersapp.feature.feed.api.UsersApi.Companion.PAGE_SIZE

abstract class CustomPagingSource<T : Any> : PagingSource<Int, T>() {
    companion object {
        const val STARTING_PAGE_INDEX = 0
    }

    abstract suspend fun invoke(size: Int, skip: Int): List<T>

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val request = invoke(PAGE_SIZE, page * PAGE_SIZE)
            LoadResult.Page(
                data = request,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page - 1,
                nextKey = if (request.isEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}