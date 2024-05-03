package com.vk.usersapp.feature.feed.api.fake

import com.vk.usersapp.feature.feed.api.UsersRepository
import com.vk.usersapp.feature.feed.model.User

class FakeErrorUsersRepository(private val api: FakeUsersApi) : UsersRepository(api) {
    private val errorMessage = "Network error"

    override suspend fun getUsers(): List<User> {
        throw Exception(errorMessage)
    }

    override suspend fun searchUsers(query: String): List<User> {
        throw Exception(errorMessage)
    }
}