package com.droidnext.countrysearch.data.model


import com.droidnext.countrysearch.domain.model.CountryDTO
import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("code")
    val code: String,
    @SerializedName("continent")
    val continent: Continent,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nameEs")
    val nameEs: String,
    @SerializedName("nameFr")
    val nameFr: String,
    @SerializedName("nameNative")
    val nameNative: String,
    @SerializedName("population")
    val population: Int
)

fun Country.toCountryDTO(): CountryDTO = CountryDTO(
    code = this.code,
    latitude = this.latitude,
    longitude = this.longitude,
    name = this.nameFr
)