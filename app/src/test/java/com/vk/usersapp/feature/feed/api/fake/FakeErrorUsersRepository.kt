package com.vk.usersapp.feature.feed.api.fake

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.api.UsersRepository
import com.vk.usersapp.feature.feed.model.User
import kotlinx.coroutines.flow.Flow

class FakeErrorUsersRepository(api: FakeUsersApi) : UsersRepository(api) {
    private val errorMessage = "Network error"

    override fun getUsers(query: String?): Flow<PagingData<User>> {
        throw Exception(errorMessage)
    }
}