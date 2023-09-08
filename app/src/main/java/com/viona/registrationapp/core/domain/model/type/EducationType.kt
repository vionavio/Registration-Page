package com.viona.registrationapp.core.domain.model.type

enum class EducationType {
    SD, SMP, SMA, S1, S2, S3;

    companion object {
        val educationValues = EducationType.values().map { it.toString() }.toTypedArray()
    }
}
