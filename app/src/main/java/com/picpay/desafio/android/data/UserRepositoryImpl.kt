package com.picpay.desafio.android.data

import android.util.Log
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
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        return withContext(ioDispatcher) {
            if (networkHelper.isConnected()) {
                Log.d("Network", "fetch from network")
                val users = remoteDataSource.getUsers()
                localDataSource.saveUsers(users)
                users
            } else {
                Log.d("Local", "fetch from local")
                localDataSource.getUsers()
            }
        }
    }
}