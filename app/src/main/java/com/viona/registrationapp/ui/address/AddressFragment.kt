package com.viona.registrationapp.ui.address

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.viona.registrationapp.R
import com.viona.registrationapp.core.ui.ViewModelFactory
import com.viona.registrationapp.databinding.FragmentAddressBinding
import com.viona.registrationapp.model.HouseType
import com.viona.registrationapp.util.observableData

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddressViewModel

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
        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this, factory)[AddressViewModel::class.java]
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
                    this@AddressFragment.findNavController().navigate(
                        R.id.action_addressFragment_to_reviewDataFragment,
                    )
                }
            }
        }
    }

    private fun setupAddress(isSubmit: Boolean? = false): Boolean {
        var result = false
        binding.apply {
            tilDomicile.helperText = getString(R.string.message_required)
            if (isSubmit == true && tieDomicile.text?.trim()?.isEmpty() == true) {
                tilDomicile.error = getString(R.string.message_input_not_empty)
            } else {
                result = true
            }

            tieDomicile.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                    Unit

                override fun afterTextChanged(s: Editable?) {
                    when {
                        s.isNullOrEmpty() -> {
                            tieDomicile.error = getString(R.string.message_input_not_empty)
                        }

                        else -> {
                            tilDomicile.error = null
                            result = true
                        }
                    }
                }
            })
        }

        return result
    }

    private fun setupHouseType(isSubmit: Boolean? = false): Boolean {
        var result = false
        binding.apply {
            tilHouseType.helperText = getString(R.string.message_required)
            if (isSubmit == true && actHouseType.text?.trim()?.isEmpty() == true) {
                tilHouseType.error = getString(R.string.message_select)
            } else {
                result = true
            }

            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                    HouseType.typeValues,
                )
            }
            actHouseType.setAdapter(adapter)
            tilHouseType.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU

            actHouseType.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                    Unit

                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) {
                        tilHouseType.error = getString(R.string.message_select)
                        result = false
                    } else {
                        tilHouseType.error = null
                        result = true
                    }
                }
            })
        }

        return result
    }

    private fun setupNoAddress(isSubmit: Boolean? = false): Boolean {
        var result = false
        binding.apply {
            tilNo.helperText = getString(R.string.message_required)
            if (isSubmit == true && tieNo.text?.trim()?.isEmpty() == true) {
                tilNo.error = getString(R.string.message_input_not_empty)
            } else {
                result = true
            }

            tieNo.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                    Unit

                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) {
                        tilNo.error = getString(R.string.message_input_not_empty)
                        result = false
                    } else {
                        tilNo.error = null
                        result = true
                    }
                }
            })
        }

        return result
    }

    private fun setupProvince(isSubmit: Boolean? = false): Boolean {
        var result = false
        binding.apply {
            tilProvince.helperText = getString(R.string.message_required)
            if (isSubmit == true && actProvince.text?.trim()?.isEmpty() == true) {
                tilProvince.error = getString(R.string.message_select)
            } else {
                result = true
            }

            viewModel.provinceData.observableData(this@AddressFragment) { result ->
                val data = result.map {
                    it.name.orEmpty()
                }.toTypedArray()

                val adapter = context?.let {
                    ArrayAdapter(
                        it,
                        androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                        data,
                    )
                }
                actProvince.setAdapter(adapter)
            }

            tilProvince.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU

            actProvince.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) = Unit

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) =
                    Unit

                override fun afterTextChanged(s: Editable?) {
                    if (s.isNullOrEmpty()) {
                        tilProvince.error = getString(R.string.message_select)
                        result = false
                    } else {
                        tilProvince.error = null
                        result = true
                    }
                }
            })
        }
        return result
    }
}
