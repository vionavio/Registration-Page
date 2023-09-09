package com.viona.registrationapp.ui.address

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.viona.registrationapp.MyApplication
import com.viona.registrationapp.R
import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.core.domain.model.type.HouseType
import com.viona.registrationapp.databinding.FragmentAddressBinding
import com.viona.registrationapp.ui.ViewModelFactory
import com.viona.registrationapp.util.Constants.EXTRA_REGISTER_PARAM
import com.viona.registrationapp.util.addAfterTextChangedListener
import com.viona.registrationapp.util.observableData
import com.viona.registrationapp.util.showSnackbar
import javax.inject.Inject

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: AddressViewModel by viewModels { factory }

    private val registerParam by lazy { arguments?.getParcelable(EXTRA_REGISTER_PARAM) as? RegisterParam }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initData() {
        viewModel.getProvinceData()
    }

    private fun initView() = with(binding) {
        setupAddress()
        setupHouseType()
        setupNoAddress()
        setupProvince()
        btnSubmit.setOnClickListener {
            when {
                !setupAddress(true) -> setupAddress(true)
                !setupHouseType(true) -> setupHouseType(true)
                !setupNoAddress(true) -> setupNoAddress(true)
                !setupProvince(true) -> setupProvince(true)
                else -> {
                    val param = RegisterParam(
                        nationalId = registerParam?.nationalId,
                        fullname = registerParam?.fullname,
                        bankAccountNo = registerParam?.bankAccountNo,
                        education = registerParam?.education,
                        dob = registerParam?.dob,
                        domicile = tieDomicile.text.toString(),
                        housingType = actHouseType.text.toString(),
                        houseNo = tieNo.text.toString(),
                        province = actProvince.text.toString(),
                    )
                    viewModel.setPersonalData(param)
                    val bundle = Bundle().apply {
                        putParcelable(EXTRA_REGISTER_PARAM, viewModel.dataParam)
                    }

                    this@AddressFragment.findNavController().navigate(
                        R.id.action_addressFragment_to_reviewDataFragment,
                        bundle,
                    )
                }
            }
        }
    }

    private fun setupAddress(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilDomicile, tieDomicile.text.toString(), isSubmit)
            tieDomicile.addAfterTextChangedListener { text ->
                when {
                    text.isNullOrEmpty() -> {
                        tieDomicile.error = validationEmptyMessage
                    }

                    else -> {
                        tilDomicile.error = null
                        result = true
                    }
                }
            }
        }

        return result
    }

    private fun setupHouseType(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilHouseType, actHouseType.text?.trim().toString(), isSubmit)
            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                    HouseType.typeValues,
                )
            }
            actHouseType.setAdapter(adapter)
            tilHouseType.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU

            actHouseType.addAfterTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    tilHouseType.error = getString(R.string.message_select)
                    result = false
                } else {
                    tilHouseType.error = null
                    result = true
                }
            }
        }

        return result
    }

    private fun setupNoAddress(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilNo, tieNo.text?.trim().toString(), isSubmit)
            tieNo.addAfterTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    tilNo.error = validationEmptyMessage
                    result = false
                } else {
                    tilNo.error = null
                    result = true
                }
            }
        }

        return result
    }

    private fun setupProvince(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilProvince, actProvince.text?.trim().toString(), isSubmit)

            viewModel.provinceData.observableData(this@AddressFragment) { result ->
                val data = result.map {
                    it.name.orEmpty()
                }.toTypedArray()
                actProvince.setOnClickListener {
                    context?.let {
                        MaterialAlertDialogBuilder(it)
                            .setTitle(getString(R.string.desc_province))
                            .setItems(data) { _, which ->
                                actProvince.setText(data[which])
                            }
                            .show()
                    }
                }
            }

            tilProvince.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU

            actProvince.addAfterTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    tilProvince.error = getString(R.string.message_select)
                    result = false
                } else {
                    tilProvince.error = null
                    result = true
                }
            }
        }
        return result
    }

    private fun onSubmitData(layout: TextInputLayout, text: String, isSubmit: Boolean?): Boolean {
        layout.helperText = getString(R.string.message_required)
        if (isSubmit == true && text.isEmpty()) {
            layout.error = validationEmptyMessage
            view?.showSnackbar(getString(R.string.message_empty))
            return false
        }

        return true
    }

    private val validationEmptyMessage get(): String = getString(R.string.message_input_not_empty)
}
