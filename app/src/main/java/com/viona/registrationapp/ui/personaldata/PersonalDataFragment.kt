package com.viona.registrationapp.ui.personaldata

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.viona.registrationapp.R
import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.core.domain.model.type.EducationType
import com.viona.registrationapp.databinding.FragmentPersonalDataBinding
import com.viona.registrationapp.util.Constants.EXTRA_REGISTER_PARAM
import com.viona.registrationapp.util.Constants.FIX_LENGTH_ID_CARD
import com.viona.registrationapp.util.Constants.MIN_LENGTH_BANK_ACCOUNT
import com.viona.registrationapp.util.showSnackbar
import java.util.Calendar

class PersonalDataFragment : Fragment() {
    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: PersonalDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPersonalDataBinding.inflate(inflater, container, false)
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
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory(),
        )[PersonalDataViewModel::class.java]
    }

    private fun setupNationalID(isSubmit: Boolean? = false): Boolean {
        var result = false
        var message = ""
        binding.apply {
            tilIdCard.helperText = getString(R.string.message_required)
            if (isSubmit == true && tieIdCard.text?.trim()?.isEmpty() == true) {
                tilIdCard.error = getString(R.string.message_input_not_empty)
                message = getString(R.string.message_input_not_empty)
            } else {
                result = true
            }

            tieIdCard.addTextChangedListener(object : TextWatcher {
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
                            tilIdCard.error = getString(R.string.message_input_not_empty)
                            message = getString(R.string.message_input_not_empty)
                        }

                        s.length != FIX_LENGTH_ID_CARD -> {
                            tilIdCard.error = getString(R.string.input_id_card)
                            message = getString(R.string.input_id_card)
                        }

                        else -> {
                            message = ""
                            tilIdCard.error = null
                            result = true
                        }
                    }
                }
            })
        }
        if (isSubmit == true && message.isNotEmpty()) {
            view?.showSnackbar(message)
        }

        return result
    }

    private fun setupFullName(isSubmit: Boolean? = false): Boolean {
        var result = false
        binding.apply {
            val inputFilter = InputFilter { source, _, _, _, _, _ ->
                source.filter { !it.isDigit() }
            }
            tieFullname.filters = arrayOf(inputFilter)
            tilFullname.helperText = getString(R.string.message_required)
            if (isSubmit == true && tieFullname.text?.trim()?.isEmpty() == true) {
                tilFullname.error = getString(R.string.message_input_not_empty)
            } else {
                result = true
            }
            tieFullname.addTextChangedListener(object : TextWatcher {
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
                        tilFullname.error = getString(R.string.message_input_not_empty)
                    } else {
                        tilFullname.error = null
                        result = true
                    }
                }
            })
        }
        return result
    }

    private fun setupBankAccountNo(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            tilBankAccount.helperText = getString(R.string.message_required)
            if (isSubmit == true && tieAccountNo.text?.trim()?.isEmpty() == true) {
                tilBankAccount.error = getString(R.string.message_input_not_empty)
                result = false
            } else {
                result = true
            }
            tieAccountNo.addTextChangedListener(object : TextWatcher {
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
                            tilBankAccount.error = getString(R.string.message_input_not_empty)
                        }

                        s.length < MIN_LENGTH_BANK_ACCOUNT -> {
                            tilBankAccount.error = "Input should be at least 8 Characters"
                        }

                        else -> {
                            tilBankAccount.error = null
                            result = true
                        }
                    }
                }
            })
        }
        return result
    }

    private fun setupEducation(isSubmit: Boolean? = false): Boolean {
        var result = false
        binding.apply {
            tilEducation.helperText = getString(R.string.message_required)
            if (isSubmit == true && actEducation.text?.trim()?.isEmpty() == true) {
                tilEducation.error = getString(R.string.message_select)
            } else {
                result = true
            }

            val adapter = context?.let {
                ArrayAdapter(
                    it,
                    androidx.transition.R.layout.support_simple_spinner_dropdown_item,
                    EducationType.educationValues,
                )
            }
            actEducation.setAdapter(adapter)
            tilEducation.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU

            actEducation.addTextChangedListener(object : TextWatcher {
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
                        tilEducation.error = getString(R.string.message_select)
                        result = false
                    } else {
                        tilEducation.error = null
                        result = true
                    }
                }
            })
        }

        return result
    }

    private fun setupDateOfBirth(isSubmit: Boolean? = false): Boolean {
        var result = false

        binding.apply {
            tilDob.helperText = getString(R.string.message_required)
            if (isSubmit == true && tieDob.text?.trim()?.isEmpty() == true) {
                tilDob.error = getString(R.string.message_input_not_empty)
            } else {
                result = true
            }
            val calendar = Calendar.getInstance()
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            tieDob.setOnClickListener {
                val picker = DatePickerDialog(
                    requireContext(),
                    { _, year, month, dayOfMonth ->
                        tieDob.setText(String.format("%d / %d / %d", dayOfMonth, month, year))
                    },
                    year,
                    month,
                    day,
                )
                picker.show()
            }
            tieDob.addTextChangedListener(object : TextWatcher {
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
                        tilDob.error = getString(R.string.message_input_not_empty)
                        result = false
                    } else {
                        tilDob.error = null
                        result = true
                    }
                }
            })
        }

        return result
    }

    private fun initView() = with(binding) {
        setupNationalID()
        setupFullName()
        setupBankAccountNo()
        setupEducation()
        setupDateOfBirth()
        btnSubmit.setOnClickListener {
            when {
                !setupNationalID(true) -> setupNationalID(true)
                !setupFullName(true) -> setupFullName(true)
                !setupBankAccountNo(true) -> setupBankAccountNo(true)
                !setupEducation(true) -> setupEducation(true)
                !setupDateOfBirth(true) -> setupDateOfBirth(true)
                else -> {
                    val param = RegisterParam(
                        nationalId = tieIdCard.text.toString(),
                        fullname = tieFullname.text.toString(),
                        bankAccountNo = tieAccountNo.text.toString(),
                        education = actEducation.text.toString(),
                        dob = tieDob.text.toString(),
                    )
                    viewModel.setPersonalData(param)

                    val bundle = Bundle().apply {
                        putParcelable(EXTRA_REGISTER_PARAM, viewModel.dataParam)
                    }
                    it.findNavController().navigate(
                        R.id.action_personalDataFragment_to_addressFragment,
                        bundle,
                    )
                }
            }
        }
    }
}
