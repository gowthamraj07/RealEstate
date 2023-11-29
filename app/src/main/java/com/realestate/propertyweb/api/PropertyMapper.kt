package com.realestate.propertyweb.api

import com.realestate.propertyweb.list.Property

internal class PropertyMapper {
    fun map(propertyDtos: List<PropertyDto>): List<Property> {

        return propertyDtos.map {
            requireNotNull(it.id) { "id is null" }
            requireNotNull(it.city) { "city is null" }
            requireNotNull(it.area) { "area is null" }
            requireNotNull(it.price) { "price is null" }
            requireNotNull(it.propertyType) { "propertyType is null" }

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
