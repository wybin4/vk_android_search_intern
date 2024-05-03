package com.vk.usersapp.feature.feed.api

import com.vk.usersapp.feature.feed.model.User

class UsersSearchPagingSource(private val api: UsersApi, private val query: String) : CustomPagingSource<User>() {
    override suspend fun invoke(size: Int, skip: Int): List<User> {
        val users = api.searchUsers(query, size, skip)
        return users.users
    }
}