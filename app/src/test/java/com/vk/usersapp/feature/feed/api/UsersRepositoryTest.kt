package com.vk.usersapp.feature.feed.api

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.PagingDataHelper.arePagingDataEqual
import com.vk.usersapp.feature.feed.api.fake.FakeUsersApi
import com.vk.usersapp.feature.feed.model.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Test

class UsersRepositoryTest {
    private val successApi: UsersApi = FakeUsersApi()
    private val successRepository = UsersRepository(successApi)

    @Test
    fun `test getUsers success`() = runBlocking {
        val users = PagingData.from(listOf(
            User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20),
            User("Мария", "Петрова", "image2.jpg", "СПбГУ", age = 30)
        ))
        val result = successRepository.getUsers(null).first()

        assertTrue(arePagingDataEqual(users, result))
    }

    @Test
    fun `test searchUsers success`() = runBlocking {
        val query = "Иван"
        val users = PagingData.from(listOf(
            User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20)
        ))
        val result = successRepository.getUsers(query).first()

        assertTrue(arePagingDataEqual(users, result))
    }

}