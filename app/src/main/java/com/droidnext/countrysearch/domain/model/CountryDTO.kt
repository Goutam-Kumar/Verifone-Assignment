package com.droidnext.countrysearch.domain.model

import com.google.gson.annotations.SerializedName

data class CountryDTO(
    @SerializedName("code")
    val code: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("name")
    val name: String,
)
