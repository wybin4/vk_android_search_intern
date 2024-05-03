package com.vk.usersapp.feature.feed.api

import com.vk.usersapp.feature.feed.model.User

class UsersPagingSource(private val api: UsersApi) : CustomPagingSource<User>() {
    override suspend fun invoke(size: Int, skip: Int): List<User> {
        val users = api.getUsers(size, skip)
        return users.users
    }
}