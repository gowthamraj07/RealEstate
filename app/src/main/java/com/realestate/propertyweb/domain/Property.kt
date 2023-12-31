package com.realestate.propertyweb.domain

import java.io.Serializable

data class Property(
    val bedrooms: Int?,
    val city: String,
    val id: Int,
    val area: Int,
    val propertyType: String,
    val url: String?,
    val price: Int,
    val professional: String,
    val offerType: Int?,
    val rooms: Int?
): Serializable