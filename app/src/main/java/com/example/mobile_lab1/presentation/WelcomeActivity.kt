package com.example.mobile_lab1.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_lab1.R
import com.example.mobile_lab1.presentation.user_list.UserAdapter
import com.example.mobile_lab1.presentation.user_list.UserViewModel
import com.example.mobile_lab1.presentation.user_list.UserViewModelProviderFactory
import timber.log.Timber

class WelcomeActivity : AppCompatActivity() {
    private val viewModel by viewModels<UserViewModel> { UserViewModelProviderFactory() }
    private val adapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val userRecyclerView: RecyclerView = findViewById(R.id.user_list)
        userRecyclerView.adapter = adapter
        userRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.welcome_container, WelcomeFragment.newInstance())
//            .commit()

        viewModel.userList.observe(this, {
            // adapter.updateList(it)
            adapter.submitList(it)
        })

        viewModel.errorMessage.observe(this, { message ->
            Timber.d(message)
        })

        viewModel.getUsers()
    }
}
