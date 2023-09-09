package com.viona.registrationapp.ui.personaldata

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.InputFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import com.viona.registrationapp.R
import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.core.domain.model.type.EducationType
import com.viona.registrationapp.databinding.FragmentPersonalDataBinding
import com.viona.registrationapp.util.Constants.EXTRA_REGISTER_PARAM
import com.viona.registrationapp.util.Constants.FIX_LENGTH_ID_CARD
import com.viona.registrationapp.util.Constants.MIN_LENGTH_BANK_ACCOUNT
import com.viona.registrationapp.util.addAfterTextChangedListener
import com.viona.registrationapp.util.showSnackbar
import java.util.Calendar

class PersonalDataFragment : Fragment() {
    private var _binding: FragmentPersonalDataBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PersonalDataViewModel by viewModels()

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
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupNationalID(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilIdCard, tieIdCard.text?.trim().toString(), isSubmit)
            tieIdCard.addAfterTextChangedListener { text ->
                when {
                    text.isNullOrEmpty() -> {
                        tilIdCard.error = validationEmptyMessage
                    }

                    text.length != FIX_LENGTH_ID_CARD -> {
                        tilIdCard.error = getString(R.string.input_id_card)
                    }

                    else -> {
                        tilIdCard.error = null
                        result = true
                    }
                }
            }
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

            result = onSubmitData(tilFullname, tieFullname.text?.trim().toString(), isSubmit)
            tieFullname.addAfterTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    tilFullname.error = getString(R.string.message_input_not_empty)
                } else {
                    tilFullname.error = null
                    result = true
                }
            }
        }
        return result
    }

    private fun setupBankAccountNo(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilBankAccount, tieAccountNo.text?.trim().toString(), isSubmit)

            tieAccountNo.addAfterTextChangedListener { text ->
                when {
                    text.isNullOrEmpty() -> {
                        tilBankAccount.error = validationEmptyMessage
                    }

                    text.length < MIN_LENGTH_BANK_ACCOUNT -> {
                        tilBankAccount.error = getString(R.string.message_input_account_no)
                    }

                    else -> {
                        tilBankAccount.error = null
                        result = true
                    }
                }
            }
        }
        return result
    }

    private fun setupEducation(isSubmit: Boolean? = false): Boolean {
        var result: Boolean
        binding.apply {
            result = onSubmitData(tilEducation, actEducation.text?.trim().toString(), isSubmit)
            actEducation.setOnClickListener {
                context?.let {
                    MaterialAlertDialogBuilder(it)
                        .setTitle(getString(R.string.desc_education))
                        .setItems(EducationType.educationValues) { _, which ->
                            actEducation.setText(EducationType.educationValues[which])
                        }
                        .show()
                }
            }
            tilEducation.endIconMode = TextInputLayout.END_ICON_DROPDOWN_MENU
            actEducation.addAfterTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    tilEducation.error = getString(R.string.message_select)
                    result = false
                } else {
                    tilEducation.error = null
                    result = true
                }
            }
        }

        return result
    }

    private fun setupDateOfBirth(isSubmit: Boolean? = false): Boolean {
        var result: Boolean

        binding.apply {
            result = onSubmitData(tilDob, tieDob.text?.trim().toString(), isSubmit)
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
            tieDob.addAfterTextChangedListener { text ->
                if (text.isNullOrEmpty()) {
                    tilDob.error = validationEmptyMessage
                    result = false
                } else {
                    tilDob.error = null
                    result = true
                }
            }
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

    private fun onSubmitData(
        layout: TextInputLayout,
        text: String,
        isSubmit: Boolean?,
    ): Boolean {
        val result: Boolean
        layout.helperText = getString(R.string.message_required)
        if (isSubmit == true && text.isEmpty()) {
            layout.error = validationEmptyMessage
            view?.showSnackbar(getString(R.string.message_empty))
            result = false
        } else {
            result = true
        }
        return result
    }

    private val validationEmptyMessage get(): String = getString(R.string.message_input_not_empty)
}
