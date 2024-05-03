package com.vk.usersapp.feature.feed.api.fake

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.api.UsersRepository
import com.vk.usersapp.feature.feed.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeEmptyUsersRepository(api: FakeUsersApi) : UsersRepository(api) {
    override fun getUsers(query: String?): Flow<PagingData<User>> = flow {
        emit(PagingData.from(emptyList()))
    }
}