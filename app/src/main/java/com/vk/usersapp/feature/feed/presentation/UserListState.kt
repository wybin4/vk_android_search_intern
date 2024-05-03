package com.vk.usersapp.feature.feed.presentation

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.model.User

data class UserListState(
    val query: String = "",
    val items: PagingData<User> = PagingData.empty(),
    val error: String? = null,
    val isLoading: Boolean = true,
)