package com.droidnext.countrysearch.data.model


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("cost")
    val cost: Int,
    @SerializedName("credits")
    val credits: Int,
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("firstPage")
    val firstPage: Int,
    @SerializedName("freeRequest")
    val freeRequest: Boolean,
    @SerializedName("lastPage")
    val lastPage: Int,
    @SerializedName("perPage")
    val perPage: Int,
    @SerializedName("total")
    val total: Int
)