package com.viona.registrationapp.core.domain.model.type

enum class HouseType {
    RUMAH,
    KANTOR;

    companion object {
        val typeValues = HouseType.values().map { it.toString() }.toTypedArray()
    }
}
