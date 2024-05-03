package com.vk.usersapp.feature.feed.api.fake

import com.vk.usersapp.feature.feed.api.UsersRepository
import com.vk.usersapp.feature.feed.model.User

class FakeSuccessUsersRepository(private val api: FakeUsersApi) : UsersRepository(api) {

    override suspend fun getUsers(): List<User> {
        return api.getUsers(Int.MAX_VALUE, 0).users
    }

    override suspend fun searchUsers(query: String): List<User> {
        return api.searchUsers(query, Int.MAX_VALUE, 0).users
    }
}