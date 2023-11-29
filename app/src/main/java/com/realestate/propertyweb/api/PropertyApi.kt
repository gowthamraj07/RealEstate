package com.realestate.propertyweb.api

import retrofit2.http.GET

interface PropertyApi {

    @GET("listings.json")
    suspend fun getProperties(): PropertiesDto

}
