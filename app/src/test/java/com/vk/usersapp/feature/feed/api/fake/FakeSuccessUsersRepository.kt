package com.vk.usersapp.feature.feed.api.fake

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.api.UsersRepository
import com.vk.usersapp.feature.feed.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSuccessUsersRepository(private val api: FakeUsersApi) : UsersRepository(api) {

    override fun getUsers(query: String?): Flow<PagingData<User>> = flow {
        val fakeUsers = api.getUsers(Int.MAX_VALUE, 0).users
        emit(PagingData.from(fakeUsers))
    }

}