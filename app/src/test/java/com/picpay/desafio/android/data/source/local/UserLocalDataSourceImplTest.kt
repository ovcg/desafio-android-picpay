package com.picpay.desafio.android.data.source.local

import com.picpay.desafio.android.data.common.dao.UserDao
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.ui.model.toEntity
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserLocalDataSourceImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    lateinit var userDao: UserDao
    private lateinit var userLocalDataSource: UserLocalDataSource

    @Before
    fun setUp() {
        userLocalDataSource = UserLocalDataSourceImpl(userDao)
    }

    @Test
    fun `should call getAll to return a list of users`() = runTest {
        val userMockk = User(id = 0, img = "img", name = "name", username = "username").toEntity()
        coEvery { userDao.getAll() } returns listOf(userMockk)
        val result = userLocalDataSource.getUsers()
        coVerify(exactly = 1) {
            userDao.getAll()
        }
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `should call getAll to return a emptyList of users`() = runTest {
        coEvery { userDao.getAll() } returns emptyList()
        val result = userLocalDataSource.getUsers()
        assertTrue(result.isEmpty())
    }

    @Test
    fun `should call saveUsers to save a list of users`() = runTest {
        coEvery { userDao.deleteAndInsertTransaction(any()) } returns Unit
        userLocalDataSource.saveUsers(listOf())
        coVerify(exactly = 1) {
            userDao.deleteAndInsertTransaction(any())
        }
    }
}