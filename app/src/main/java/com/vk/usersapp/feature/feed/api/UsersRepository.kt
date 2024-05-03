package com.vk.usersapp.feature.feed.api

import com.vk.usersapp.core.Retrofit
import com.vk.usersapp.feature.feed.model.User

class UsersRepository {
    private val api: UsersApi by lazy { Retrofit.getClient().create(UsersApi::class.java) }

    suspend fun getUsers(): List<User> {
        return api.getUsers(
            limit = Int.MAX_VALUE,
            skip = 0
        ).users
    }

    suspend fun searchUsers(query: String): List<User> {
        return api.searchUsers(
            query = query,
            limit = Int.MAX_VALUE,
            skip = 0
        ).users
    }
}