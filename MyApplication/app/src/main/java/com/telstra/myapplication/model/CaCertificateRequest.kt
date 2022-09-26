package com.telstra.myapplication.model

data class CaCertificateRequest(
    val altNames: List<Any>,
    val basicConstraints: String,
    val basicConstraintsCA: String,
    val commonName: String,
    val csrSignAlgo : String,
    val countryName: String,
    val days: Int,
    val emailAddress: String,
    val keyUsage: String,
    val localityName: String,
    val organizationName: String,
    val organizationalUnitName: String,
    val stateOrProvinceName: String
)