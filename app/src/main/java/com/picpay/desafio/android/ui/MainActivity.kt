package com.picpay.desafio.android.ui

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.ui.theme.UserAppTheme
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var myAdapter: UserListAdapter
    private val viewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setUpViews()
        // observer()
        setContent {
            val state by viewModel.userState.observeAsState()
            UserAppTheme { UserScreen(userState = state) {} }
        }
    }

    private fun showErrorMessage() {
        val message = getString(R.string.error)
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

    /*override fun onResume() {
        super.onResume()
        viewModel.getUsers()
    }

    private fun setUpViews() {
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.user_list_progress_bar)

        myAdapter = UserListAdapter()
        recyclerView.apply {
            adapter = myAdapter
            recyclerView.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun observer() {
        viewModel.userState.observe(this) { state ->
            when (state) {
                is UserState.Loading -> {
                    showProgressBar()
                }

                is UserState.Error -> {
                    showProgressBar(false)
                    val message = getString(R.string.error)
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                        .show()
                }

                is UserState.Success -> {
                    showProgressBar(false)
                    myAdapter.users = state.users
                }
            }
        }
    }

    private fun showProgressBar(show: Boolean = true) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }*/
}
