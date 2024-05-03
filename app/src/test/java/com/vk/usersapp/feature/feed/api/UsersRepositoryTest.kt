package com.vk.usersapp.feature.feed.api

import com.vk.usersapp.feature.feed.api.fake.FakeUsersApi
import com.vk.usersapp.feature.feed.model.User
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UsersRepositoryTest {
    private val api: UsersApi = FakeUsersApi()
    private val repository = UsersRepository(api)

    @Test
    fun `test getUsers`() = runBlocking {
        val users = listOf(
            User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20),
            User("Мария", "Петрова", "image2.jpg", "СПбГУ", age = 30)
        )
        val result = repository.getUsers()

        assertEquals(users, result)
    }

    @Test
    fun `test searchUsers`() = runBlocking {
        val query = "Иван"
        val users = listOf(
            User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20)
        )
        val result = repository.searchUsers(query)

        assertEquals(users, result)
    }
}