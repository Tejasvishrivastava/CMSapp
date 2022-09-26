package com.telstra.myapplication.model

data class SignedCertificateResponse(
    val cert: String,
    val certid: String,
    val csr: String,
    val pk: String,
    val success: String
)