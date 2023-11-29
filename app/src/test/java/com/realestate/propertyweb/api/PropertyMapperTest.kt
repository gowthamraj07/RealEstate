package com.realestate.propertyweb.api

import com.realestate.propertyweb.list.Property
import com.realestate.propertyweb.list.gen
import com.realestate.propertyweb.list.propertyDtoArb
import com.realestate.propertyweb.list.propertyItemDtoArb
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.property.Arb

class PropertyMapperTest : StringSpec({

    val mapper = PropertyMapper()

    "should map the provided propertyDtos to list of Property domains" {
        val propertyItemDto = Arb.propertyItemDtoArb.gen()
        val propertyDto = Arb.propertyDtoArb.gen().copy(items = listOf(propertyItemDto), totalCount = 1)

        val result = mapper.map(propertyDto)

        result shouldBe listOf(
            Property(
                city = propertyItemDto.city!!,
                id = propertyItemDto.id!!,
                area = propertyItemDto.area!!,
                price = propertyItemDto.price!!,
                propertyType = propertyItemDto.propertyType!!,
                bedrooms = propertyItemDto.bedrooms,
                url = propertyItemDto.url,
                professional = propertyItemDto.professional,
                offerType = propertyItemDto.offerType,
                rooms = propertyItemDto.rooms,
            )
        )
    }

    "throw exception when id is null" {
        val propertyItemDto = Arb.propertyItemDtoArb.gen().copy(id = null)
        val propertyDto = Arb.propertyDtoArb.gen().copy(items = listOf(propertyItemDto), totalCount = 1)

        val result = runCatching { mapper.map(propertyDto) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("id is null")
    }

    "throw exception when city is null" {
        val propertyItemDto = Arb.propertyItemDtoArb.gen().copy(city = null)
        val propertyDto = Arb.propertyDtoArb.gen().copy(items = listOf(propertyItemDto), totalCount = 1)

        val result = runCatching { mapper.map(propertyDto) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("city is null")
    }

    "throw exception when area is null" {
        val propertyItemDto = Arb.propertyItemDtoArb.gen().copy(area = null)
        val propertyDto = Arb.propertyDtoArb.gen().copy(items = listOf(propertyItemDto), totalCount = 1)

        val result = runCatching { mapper.map(propertyDto) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("area is null")
    }

    "throw exception when price is null" {
        val propertyItemDto = Arb.propertyItemDtoArb.gen().copy(price = null)
        val propertyDto = Arb.propertyDtoArb.gen().copy(items = listOf(propertyItemDto), totalCount = 1)

        val result = runCatching { mapper.map(propertyDto) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("price is null")
    }

    "throw exception when propertyType is null" {
        val propertyItemDto = Arb.propertyItemDtoArb.gen().copy(propertyType = null)
        val propertyDto = Arb.propertyDtoArb.gen().copy(items = listOf(propertyItemDto), totalCount = 1)

        val result = runCatching { mapper.map(propertyDto) }

        result.exceptionOrNull() shouldBe IllegalArgumentException("propertyType is null")
    }
})