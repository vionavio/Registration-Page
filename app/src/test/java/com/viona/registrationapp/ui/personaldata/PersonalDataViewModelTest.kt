package com.viona.registrationapp.ui.personaldata

import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.core.domain.model.type.EducationType
import com.viona.registrationapp.core.domain.model.type.HouseType
import org.junit.Before
import org.junit.Test

class PersonalDataViewModelTest {

    private lateinit var registerParam: RegisterParam

    private lateinit var viewModel: PersonalDataViewModel

    @Before
    fun setup() {
        viewModel = PersonalDataViewModel()
        registerParam = RegisterParam(
            nationalId = "1231231231231231",
            fullname = "Viona",
            bankAccountNo = "12121212",
            education = EducationType.S2.name,
            dob = "10/10/1990",
            domicile = "Yogyakarta",
            housingType = HouseType.RUMAH.name,
            houseNo = "2312A1",
            province = "JAWA TENGAH",
        )
    }

    @Test
    fun `setPersonalData should update _dataParam correctly`() {
        // Given
        val param = registerParam

        // When
        viewModel.setPersonalData(param)

        // Then
        val dataParam = viewModel.dataParam
        assert(dataParam === param)
    }
}
