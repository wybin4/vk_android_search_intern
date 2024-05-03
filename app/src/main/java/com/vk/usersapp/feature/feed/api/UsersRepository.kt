package com.vk.usersapp.feature.feed.api

import com.vk.usersapp.core.Retrofit
import com.vk.usersapp.feature.feed.model.User

open class UsersRepository(private val api: UsersApi = defaultUsersApi) {
    companion object {
        private val defaultUsersApi: UsersApi by lazy { Retrofit.getClient().create(UsersApi::class.java) }
    }

    open suspend fun getUsers(): List<User> {
        return api.getUsers(
            limit = Int.MAX_VALUE,
            skip = 0
        ).users
    }

    open suspend fun searchUsers(query: String): List<User> {
        return api.searchUsers(
            query = query,
            limit = Int.MAX_VALUE,
            skip = 0
        ).users
    }
}