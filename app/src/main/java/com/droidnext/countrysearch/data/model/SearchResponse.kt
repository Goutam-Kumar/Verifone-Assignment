package com.droidnext.countrysearch.data.model


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("countries")
    val countries: List<Country>,
    @SerializedName("meta")
    val meta: Meta
)