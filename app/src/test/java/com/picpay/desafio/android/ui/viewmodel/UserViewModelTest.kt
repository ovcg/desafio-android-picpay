package com.picpay.desafio.android.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.model.UserState
import io.mockk.coEvery
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
class UserViewModelTest {

    @get:Rule val mockKRule = MockKRule(this)
    @get:Rule val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: UserViewModel
    private val repository: UserRepository = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = UserViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when getUsers is called, then loading state is emitted`() {
        val expected = UserState.Loading

        assertEquals(expected, viewModel.userState.value)
    }

    @Test
    fun `when getUsers is called, then repository getUsers is called`() {
        val result = Result.success(listOf(mockk<User>()))
        val expected = UserState.Success(result.getOrNull()!!)
        coEvery { repository.getUsers() } returns result

        viewModel.getUsers()
        val actual = viewModel.userState.replayCache
        assertEquals(expected, actual.last())
    }

    @Test
    fun `when getUsers is called, then repository returns error`() {
        val result = Result.failure<List<User>>(Exception("error"))
        val expected = UserState.Error
        coEvery { repository.getUsers() } returns result

        viewModel.getUsers()
        val actual = viewModel.userState.value
        assertEquals(expected, actual)
    }
}
