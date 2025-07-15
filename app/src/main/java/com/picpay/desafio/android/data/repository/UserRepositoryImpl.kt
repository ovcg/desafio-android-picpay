package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.source.local.UserLocalDataSource
import com.picpay.desafio.android.data.source.network.UserRemoteDataSource
import com.picpay.desafio.android.ui.model.User
import com.picpay.desafio.android.util.NetworkHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource,
    private val networkHelper: NetworkHelper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : UserRepository {

    override suspend fun getUsers(): Result<List<User>> {
        return withContext(ioDispatcher) {
            try {
                if (networkHelper.isConnected()) {
                    val users = remoteDataSource.getUsers()
                    localDataSource.saveUsers(users)
                    Result.success(users)
                } else {
                    val localUsers = localDataSource.getUsers()
                    Result.success(localUsers)
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
