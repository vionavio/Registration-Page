package com.viona.registrationapp.model

enum class HouseType {
    RUMAH,
    KANTOR;

    companion object {
        val typeValues = HouseType.values().map { it.toString() }.toTypedArray()
    }
}
