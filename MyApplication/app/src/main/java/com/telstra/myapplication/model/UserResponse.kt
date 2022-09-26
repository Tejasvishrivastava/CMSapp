package com.example.retrofitlibrary.model

data class UserResponse(
    val token: String,
    val user: UserX,
    val error: String
)