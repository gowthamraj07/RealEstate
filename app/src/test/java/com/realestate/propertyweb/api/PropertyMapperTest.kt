package com.realestate.propertyweb.api

import com.realestate.propertyweb.list.Property
import com.realestate.propertyweb.list.gen
import com.realestate.propertyweb.list.propertyDtoArb
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
                city = propertyDto.city!!,
                id = propertyDto.id!!,
                area = propertyDto.area!!,
                price = propertyDto.price!!,
                propertyType = propertyDto.propertyType!!,
                bedrooms = propertyDto.bedrooms,
                url = propertyDto.url,
                professional = propertyDto.professional,
                offerType = propertyDto.offerType,
                rooms = propertyDto.rooms,
            )
        )
    }

    "throw exception when id is null" {
        val propertyDto = Arb.propertyDtoArb.gen().copy(id = null)

        val result = runCatching { mapper.map(listOf(propertyDto)) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("id is null")
    }

    "throw exception when city is null" {
        val propertyDto = Arb.propertyDtoArb.gen().copy(city = null)

        val result = runCatching { mapper.map(listOf(propertyDto)) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("city is null")
    }

    "throw exception when area is null" {
        val propertyDto = Arb.propertyDtoArb.gen().copy(area = null)

        val result = runCatching { mapper.map(listOf(propertyDto)) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("area is null")
    }

    "throw exception when price is null" {
        val propertyDto = Arb.propertyDtoArb.gen().copy(price = null)

        val result = runCatching { mapper.map(listOf(propertyDto)) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("price is null")
    }

    "throw exception when propertyType is null" {
        val propertyDto = Arb.propertyDtoArb.gen().copy(propertyType = null)

        val result = runCatching { mapper.map(listOf(propertyDto)) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("propertyType is null")
    }
})