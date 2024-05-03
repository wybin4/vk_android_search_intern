package com.vk.usersapp.feature.feed.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vk.usersapp.core.Retrofit
import com.vk.usersapp.feature.feed.api.UsersApi.Companion.PAGE_SIZE
import com.vk.usersapp.feature.feed.model.User
import kotlinx.coroutines.flow.Flow

open class UsersRepository(private val api: UsersApi = defaultUsersApi) {
    companion object {
        private val defaultUsersApi: UsersApi by lazy { Retrofit.getClient().create(UsersApi::class.java) }
    }

    open fun getUsers(query: String?): Flow<PagingData<User>> {
        return if (query != null) {
            Pager(
                config = PagingConfig(pageSize = PAGE_SIZE),
                pagingSourceFactory = { UsersSearchPagingSource(api, query) }
            ).flow
        } else {
            Pager(
                config = PagingConfig(pageSize = PAGE_SIZE),
                pagingSourceFactory = { UsersPagingSource(api) }
            ).flow
        }
    }
}