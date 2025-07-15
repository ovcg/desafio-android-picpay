package com.picpay.desafio.android.data.source.network

import com.picpay.desafio.android.data.service.PicPayService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlin.test.assertFails
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRemoteDataSourceImplTest {

    @get:Rule val mockkRule = MockKRule(this)

    @MockK lateinit var service: PicPayService
    private lateinit var dataSource: UserRemoteDataSource

    @Before
    fun setUp() {
        dataSource = UserRemoteDataSourceImpl(service)
    }

    @Test
    fun `when call getUsers then call service getUsers`() = runTest {
        coEvery { service.getUsers() } returns listOf(mockk())

        val result = dataSource.getUsers()

        coVerify(exactly = 1) { service.getUsers() }
        assertTrue(result.isNotEmpty())
    }

    @Test
    fun `when call getUsers then returns empty list`() = runTest {
        coEvery { service.getUsers() } returns emptyList()

        val result = dataSource.getUsers()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `when call getUsers then returns error`() = runTest {
        coEvery { service.getUsers() } throws Exception("error")

        assertFails { dataSource.getUsers() }
    }
}
