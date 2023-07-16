package com.azalia.suitmediatestkm.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.azalia.suitmediatestkm.data.Repository
import com.azalia.suitmediatestkm.data.Resource

class LoginViewModel(private val repository: Repository) : ViewModel() {
    fun userLogin(name: String): LiveData<Resource<String>> = repository.userLogin(name)
}