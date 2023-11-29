package com.realestate.propertyweb.api

import com.realestate.propertyweb.list.Property

internal class PropertyMapper {
    fun map(propertyDtos: List<PropertyDto>): List<Property> {

        return propertyDtos.map {
            Property(
                bedrooms = it.bedrooms!!,
                city = it.city!!,
                id = it.id!!,
                area = it.area!!,
                url = it.url!!,
                price = it.price!!,
                professional = it.professional!!,
                propertyType = it.propertyType!!,
                offerType = it.offerType!!,
                rooms = it.rooms!!,
            )
        }
    }
}
