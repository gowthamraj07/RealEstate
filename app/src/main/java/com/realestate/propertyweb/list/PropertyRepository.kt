package com.realestate.propertyweb.list

import com.realestate.propertyweb.api.PropertyApi
import com.realestate.propertyweb.api.PropertyMapper
import com.realestate.propertyweb.domain.Property

internal class PropertyRepository(private val api: PropertyApi, private val mapper: PropertyMapper) {
    suspend fun getProperties(): List<Property> {
        val propertyDtos = api.getProperties()
        return mapper.map(propertyDtos)
    }

}
