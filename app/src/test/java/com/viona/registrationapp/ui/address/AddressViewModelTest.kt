package com.viona.registrationapp.ui.address

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.viona.registrationapp.core.data.Resource
import com.viona.registrationapp.core.domain.model.Province
import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.core.domain.model.type.EducationType
import com.viona.registrationapp.core.domain.model.type.HouseType
import com.viona.registrationapp.core.domain.usecase.ProvinceUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class AddressViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockProvinceUseCase: ProvinceUseCase

    private lateinit var mockProvinceObserver: Observer<List<Province>>

    private lateinit var viewModel: AddressViewModel
    private val testDispatchers = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatchers)
        mockProvinceUseCase = mockk(relaxed = true)
        mockProvinceObserver = mockk(relaxed = true)

        viewModel = AddressViewModel(mockProvinceUseCase)
        viewModel.provinceData.observeForever(mockProvinceObserver)
    }

    @Test
    fun `getProvinceData should update provinceData correctly`() = runBlocking {
        // Given
        val dummyProvinceList = listOf(Province(id = "1", name = "Province A"))
        every { mockProvinceUseCase.getProvince() } returns flowOf(Resource.Success(dummyProvinceList))

        // When
        viewModel.getProvinceData()

        // Then
        verify { mockProvinceObserver.onChanged(dummyProvinceList) }
        assertEquals(dummyProvinceList, viewModel.provinceData.value)
    }

    @Test
    fun `setPersonalData should update dataParam correctly`() {
        // Given
        val param = RegisterParam(
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

        // When
        viewModel.setPersonalData(param)

        // Then
        assertEquals(param, viewModel.dataParam)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }
}
