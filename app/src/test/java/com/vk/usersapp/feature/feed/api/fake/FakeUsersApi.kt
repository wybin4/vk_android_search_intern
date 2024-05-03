package com.vk.usersapp.feature.feed.api.fake

import com.vk.usersapp.feature.feed.api.UsersApi
import com.vk.usersapp.feature.feed.model.User
import com.vk.usersapp.feature.feed.model.UsersResponse

class FakeUsersApi : UsersApi {
    override suspend fun getUsers(limit: Int, skip: Int): UsersResponse {
        val users = listOf(
            User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20),
            User("Мария", "Петрова", "image2.jpg", "СПбГУ", age = 30)
        )
        return UsersResponse(users, total = users.size, skip = skip, limit = limit)
    }

    override suspend fun searchUsers(query: String, limit: Int, skip: Int): UsersResponse {
        val users = if (query == "Иван") {
            listOf(User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20))
        } else {
            listOf(User("Мария", "Петрова", "image2.jpg", "СПбГУ", age = 30))
        }
        return UsersResponse(users, total = users.size, skip = skip, limit = limit)
    }
}