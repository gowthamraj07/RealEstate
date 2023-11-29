package com.realestate.propertyweb.api

import com.google.gson.annotations.SerializedName

data class PropertyDto(
    @SerializedName("bedrooms") var bedrooms: Int? = null,
    @SerializedName("city") var city: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("area") var area: Int? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("price") var price: Int? = null,
    @SerializedName("professional") var professional: String? = null,
    @SerializedName("propertyType") var propertyType: String? = null,
    @SerializedName("offerType") var offerType: Int? = null,
    @SerializedName("rooms") var rooms: Int? = null
)