package com.vk.usersapp.feature.feed.presentation

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.model.User

sealed interface UserListAction {
    data object Init : UserListAction
    data class QueryChanged(val query: String) : UserListAction
    data class UsersLoaded(val users: PagingData<User>) : UserListAction
    data class LoadError(val error: String) : UserListAction
}