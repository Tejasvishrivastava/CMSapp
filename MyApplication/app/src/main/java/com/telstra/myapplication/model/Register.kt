package com.telstra.myapplication.model

data class Register(
    val department: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
    val userType: String
)