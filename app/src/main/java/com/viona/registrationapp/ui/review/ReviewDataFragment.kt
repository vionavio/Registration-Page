package com.viona.registrationapp.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.viona.registrationapp.R
import com.viona.registrationapp.core.domain.model.param.RegisterParam
import com.viona.registrationapp.databinding.FragmentReviewDataBinding
import com.viona.registrationapp.util.Constants

class ReviewDataFragment : Fragment() {
    private var _binding: FragmentReviewDataBinding? = null
    private val binding get() = _binding!!

    private val registerParam by lazy { arguments?.getParcelable(Constants.EXTRA_REGISTER_PARAM) as? RegisterParam }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentReviewDataBinding.inflate(inflater, container, false)
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

    private fun initView() = with(binding) {
        registerParam?.run {
            tvNationalId.text = getString(R.string.text_id_card, nationalId)
            tvFullname.text = getString(R.string.text_fullname, fullname)
            tvAccountNo.text = getString(R.string.text_account_no, bankAccountNo.toString())
            tvEducation.text = getString(R.string.text_education, education)
            tvDob.text = getString(R.string.text_dob, dob)
            tvHousingType.text = getString(R.string.text_home_type, housingType)
            tvAddress.text = getString(R.string.text_address, "$domicile $houseNo $province")
        }

        btnFinish.setOnClickListener {
            this@ReviewDataFragment.findNavController()
                .navigate(R.id.action_reviewDataFragment_to_homeFragment)
        }
    }
}
