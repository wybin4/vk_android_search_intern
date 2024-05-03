package com.vk.usersapp.feature.feed.api.fake

import com.vk.usersapp.feature.feed.api.UsersRepository
import com.vk.usersapp.feature.feed.model.User

class FakeEmptyUsersRepository(private val api: FakeUsersApi) : UsersRepository(api) {

    override suspend fun getUsers(): List<User> {
        return emptyList()
    }

    override suspend fun searchUsers(query: String): List<User> {
        return emptyList()
    }
}