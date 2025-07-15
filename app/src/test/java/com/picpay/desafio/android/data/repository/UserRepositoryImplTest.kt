package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.source.local.UserLocalDataSource
import com.picpay.desafio.android.data.source.network.UserRemoteDataSource
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.util.NetworkHelper
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule

class UserRepositoryImplTest {

    @get:Rule var mockKRule = MockKRule(this)
    @MockK private lateinit var localDataSource: UserLocalDataSource
    @MockK private lateinit var remoteDataSource: UserRemoteDataSource
    @MockK private lateinit var networkHelper: NetworkHelper
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        repository = UserRepositoryImpl(remoteDataSource, localDataSource, networkHelper)
    }

    @Test
    fun `when getUsers with internet connection returns list of users and save in local data source`() =
        runTest {
            val usersMocked = listOf(mockk<User>())
            coEvery { remoteDataSource.getUsers() } returns usersMocked
            coEvery { localDataSource.saveUsers(any()) } returns Unit
            every { networkHelper.isConnected() } returns true

            val users = repository.getUsers()

            assertTrue { users.isSuccess }
            assertTrue { users.getOrNull()!!.isNotEmpty() }
        }

    @Test
    fun `when getUsers without internet connection returns list of users from local data source`() =
        runTest {
            val usersMocked = listOf(mockk<User>())
            coEvery { localDataSource.getUsers() } returns usersMocked
            every { networkHelper.isConnected() } returns false

            val users = repository.getUsers()

            assertTrue { users.isSuccess }
            assertTrue { users.getOrNull()!!.isNotEmpty() }
        }

    @Test
    fun `when getUsers fails returns error`() = runTest {
        coEvery { localDataSource.getUsers() } throws Exception("error")
        every { networkHelper.isConnected() } returns false

        val users = repository.getUsers()

        assertTrue { users.isFailure }
        assertNotNull(users.exceptionOrNull())
    }
}
