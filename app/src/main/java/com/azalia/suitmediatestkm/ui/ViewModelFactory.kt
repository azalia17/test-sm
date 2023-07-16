package com.azalia.suitmediatestkm.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.ExperimentalPagingApi
import com.azalia.suitmediatestkm.Injection
import com.azalia.suitmediatestkm.data.Repository
import com.azalia.suitmediatestkm.ui.list.UserListViewModel
import com.azalia.suitmediatestkm.ui.login.LoginViewModel
import com.azalia.suitmediatestkm.ui.main.MainViewModel

class ViewModelFactory constructor(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
    @OptIn(ExperimentalPagingApi::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(UserListViewModel::class.java) -> {
                UserListViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
    }
}