package com.realestate.propertyweb.list

data class Property(
    val bedrooms: Int?,
    val city: String,
    val id: Int,
    val area: Int,
    val url: String?,
    val price: Int,
    val professional: String?,
    val propertyType: String?,
    val offerType: Int?,
    val rooms: Int?
)