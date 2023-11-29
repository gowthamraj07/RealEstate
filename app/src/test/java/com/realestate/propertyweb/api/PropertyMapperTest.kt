package com.realestate.propertyweb.api

import com.realestate.propertyweb.list.Property
import com.realestate.propertyweb.list.gen
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.string

class PropertyMapperTest : StringSpec({

    val mapper = PropertyMapper()

    "should map the provided propertyDtos to list of Property domains" {
        val propertyDto = PropertyDto(
            bedrooms = Arb.int().gen(),
            city = Arb.string().gen(),
            id = Arb.int().gen(),
            area = Arb.int().gen(),
            url = Arb.string().gen(),
            price = Arb.int().gen(),
            professional = Arb.string().gen(),
            propertyType = Arb.string().gen(),
            offerType = Arb.int().gen(),
            rooms = Arb.int().gen(),
        )

        val result = mapper.map(listOf(propertyDto))

        result shouldBe listOf(
            Property(
                bedrooms = propertyDto.bedrooms!!,
                city = propertyDto.city!!,
                id = propertyDto.id!!,
                area = propertyDto.area!!,
                url = propertyDto.url!!,
                price = propertyDto.price!!,
                professional = propertyDto.professional!!,
                propertyType = propertyDto.propertyType!!,
                offerType = propertyDto.offerType!!,
                rooms = propertyDto.rooms!!,
            )
        )
    }
})