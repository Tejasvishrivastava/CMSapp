package com.telstra.myapplication.model

data class SelfSignRequest(
    val altNames: List<String>,
    val basicConstraints: String,
    val basicConstraintsCA: String,
    val commonName: String,
    val countryName: String,
    val csrSignAlgo: String,
    val days: Int,
    val emailAddress: String,
    val keyBitSize: Int,
    val keyUsage: String,
    val localityName: String,
    val organizationName: String,
    val organizationalUnitName: String,
    val stateOrProvinceName: String
)