package com.viona.registrationapp.ui.review

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.viona.registrationapp.R
import com.viona.registrationapp.databinding.FragmentReviewDataBinding

class ReviewDataFragment : Fragment() {
    private var _binding: FragmentReviewDataBinding? = null
    private val binding get() = _binding!!

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
        btnFinish.setOnClickListener {
            this@ReviewDataFragment.findNavController()
                .navigate(R.id.action_reviewDataFragment_to_homeFragment)
        }
    }
}
