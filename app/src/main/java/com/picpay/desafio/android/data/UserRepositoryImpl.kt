package com.picpay.desafio.android.data

import com.picpay.desafio.android.data.source.local.UserLocalDataSource
import com.picpay.desafio.android.data.source.network.UserRemoteDataSource
import com.picpay.desafio.android.ui.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val isNetworkAvailable: Boolean = false,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {
    override suspend fun getUsers(): List<User> {
        return withContext(ioDispatcher) {
            if (isNetworkAvailable) {
                val users = remoteDataSource.getUsers()
                localDataSource.deleteAll()
                localDataSource.saveUsers(users)
                users
            } else {
                localDataSource.getUsers()
            }
        }
    }
}