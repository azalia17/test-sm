package com.azalia.suitmediatestkm.ui.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.azalia.suitmediatestkm.R
import com.azalia.suitmediatestkm.databinding.ActivityUserListBinding
import com.azalia.suitmediatestkm.ui.ViewModelFactory
import com.azalia.suitmediatestkm.ui.adapter.LoadingStateAdapter
import com.azalia.suitmediatestkm.ui.adapter.UserAdapter
import com.azalia.suitmediatestkm.ui.login.LoginViewModel

class UserListActivity : AppCompatActivity() {

    private lateinit var userListBinding: ActivityUserListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userListBinding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(userListBinding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val userLoginViewModel: UserListViewModel by viewModels {
            factory
        }

        userListBinding.rvListUser.layoutManager = LinearLayoutManager(this)

//        getData()
        val adapter = UserAdapter()
        userListBinding.rvListUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter{
                adapter.retry()
            }
        )
        userLoginViewModel.user.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }

    private fun getData() {

    }
}