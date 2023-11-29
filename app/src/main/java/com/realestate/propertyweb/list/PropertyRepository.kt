package com.realestate.propertyweb.list

import com.realestate.propertyweb.api.PropertyApi

internal class PropertyRepository(private val api: PropertyApi) {
    fun getProperties(): List<Property> {
        api.getProperties()
        return emptyList()
    }

}
