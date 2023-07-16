package com.azalia.suitmediatestkm.data.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserListResponse (
    @field:Json(name = "page")
    val page: Int,

    @field:Json(name = "per_page")
    val per_page: Int,

    @field:Json(name = "total")
    val total: Int,

    @field:Json(name = "total_pages")
    val total_pages: Int,

    @field:Json(name = "data")
    val data: List<User>,
)

data class User (
    @field:Json(name = "id")
    val id: Int,

    @field:Json(name = "email")
    val email: String,

    @field:Json(name = "first_name")
    val first_name: String,

    @field:Json(name = "last_name")
    val last_name: String,

    @field:Json(name = "avatar")
    val avatar: String,
)