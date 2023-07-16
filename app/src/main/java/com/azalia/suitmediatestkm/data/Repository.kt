package com.azalia.suitmediatestkm.data

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.azalia.suitmediatestkm.UserPreference
import com.azalia.suitmediatestkm.data.remote.ApiService
import com.azalia.suitmediatestkm.data.remote.RetrofitService
import com.azalia.suitmediatestkm.data.response.User
import com.azalia.suitmediatestkm.data.response.UserListResponse
import java.lang.Exception

class Repository constructor(private val pref: UserPreference) {
    private val retrofit: ApiService = RetrofitService.create()

//    fun getUser(page: Int, per_page: Int): LiveData<Resource<UserListResponse>> = liveData {
//        try {
//            emit(Resource.Loading())
//            val response = retrofit.getUsers(page, per_page)
//            emit(Resource.Success(response))
//        } catch (e: Exception) {
//            e.printStackTrace()
//            Log.e(TAG, "getUser: ${e.toString()}")
//            emit(Resource.Failure(e.toString()))
//        }
//    }

    fun userLogin(name: String): LiveData<Resource<String>> = liveData {
        try {
            pref.saveUser(name)
        } catch (e: Exception) {
            emit(Resource.Failure(e.toString()))
        }
    }

    fun getUserName(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = {
                UserPagingSource(apiService = retrofit)
            }
        ).liveData
    }

    fun getUser(): LiveData<String?> = pref.getUser().asLiveData()
}