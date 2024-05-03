package com.vk.usersapp.feature.feed.presentation

import androidx.paging.PagingData
import com.vk.usersapp.feature.feed.PagingDataHelper.arePagingDataEqual
import com.vk.usersapp.feature.feed.api.fake.FakeEmptyUsersRepository
import com.vk.usersapp.feature.feed.api.fake.FakeErrorUsersRepository
import com.vk.usersapp.feature.feed.api.fake.FakeUsersApi
import com.vk.usersapp.feature.feed.api.fake.FakeSuccessUsersRepository
import com.vk.usersapp.feature.feed.model.User
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListFeatureTest {
    private val api = FakeUsersApi()
    private val users = PagingData.from(listOf(
        User("Иван", "Иванов", "image1.jpg", "МГУ", age = 20),
        User("Мария", "Петрова", "image2.jpg", "СПбГУ", age = 30)
    ))

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getUsers success`() = runTest {
        val userListFeature = UserListFeature(FakeSuccessUsersRepository(api))

        userListFeature.submitAction(UserListAction.Init)

        val viewState = userListFeature.viewStateFlow.first()
        assertTrue(viewState is UserListViewState.List)
        assertEquals(true,
            arePagingDataEqual(
                users,
                (viewState as UserListViewState.List).itemsList
            )
        )
    }

    @Test
    fun `test getUsers error`() = runBlocking {
        val userListFeature = UserListFeature(FakeErrorUsersRepository(api))

        userListFeature.submitAction(UserListAction.Init)

        delay(10)

        val viewState = userListFeature.viewStateFlow.first()
        assertTrue(viewState is UserListViewState.Error)
        assertEquals("Network error", (viewState as UserListViewState.Error).errorText)
    }

    @Test
    fun `test getUsers with query`() = runTest {
        val userListFeature = UserListFeature(FakeSuccessUsersRepository(api))

        userListFeature.submitAction(UserListAction.Init)
        userListFeature.submitAction(UserListAction.QueryChanged("query"))

        val viewState = userListFeature.viewStateFlow.first()
        assertTrue(viewState is UserListViewState.List)
        assertTrue(arePagingDataEqual(
            users, (viewState as UserListViewState.List).itemsList)
        )
    }

    @Test
    fun `test getUsers with empty result`() = runBlocking {
        val userListFeature = UserListFeature(FakeEmptyUsersRepository(api))

        userListFeature.submitAction(UserListAction.Init)

        delay(10)

        val viewState = userListFeature.viewStateFlow.first()
        assertTrue(viewState is UserListViewState.List)
        assertTrue(arePagingDataEqual(
            PagingData.empty(), (viewState as UserListViewState.List).itemsList
        ))
    }

}