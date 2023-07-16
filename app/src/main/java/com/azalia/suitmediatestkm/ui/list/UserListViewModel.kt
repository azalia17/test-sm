package com.azalia.suitmediatestkm.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.azalia.suitmediatestkm.data.Repository
import com.azalia.suitmediatestkm.data.Resource
import com.azalia.suitmediatestkm.data.response.User

class UserListViewModel(private val repository: Repository) : ViewModel() {
    val user: LiveData<PagingData<User>> =
        repository.getUserName().cachedIn(viewModelScope)
}