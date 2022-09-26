package com.telstra.myapplication.model

data class RegisterResponse(
    val email: String,
    val token: String,
    val userId: String
)