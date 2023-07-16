package com.azalia.suitmediatestkm.data.remote

import com.azalia.suitmediatestkm.data.response.UserListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users?page={page}&per_page={per_page}")
    fun getUsers (
        @Path("page") page: Int,
        @Path("per_page") per_page: Int
    ): UserListResponse
}