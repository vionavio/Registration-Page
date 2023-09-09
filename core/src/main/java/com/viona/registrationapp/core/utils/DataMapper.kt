package com.viona.registrationapp.core.utils

import com.viona.registrationapp.core.data.source.response.DataItemResponseData
import com.viona.registrationapp.core.domain.model.Province

object DataMapper {
    fun mapResponseToDomain(input: List<DataItemResponseData>): List<Province> =
        input.map {
            Province(
                id = it.id,
                name = it.name,
            )
        }
}
