package com.viona.registrationapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.viona.registrationapp.databinding.ActivityMainBinding
import com.viona.registrationapp.util.Constants
import com.viona.registrationapp.util.setGone
import com.viona.registrationapp.util.setVisibleIfElseGone

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupNavigationController()
    }

    private fun setupView() = with(binding) {
        pbRegistration.setGone()
        toolbar.setGone()
    }

    private fun setupToolbarVisibility(
        isVisible: Boolean,
        title: String? = "",
        progressBar: Int? = 0,
    ) = with(binding) {
        toolbar.setVisibleIfElseGone(isVisible)
        pbRegistration.setVisibleIfElseGone(isVisible)
        if (toolbar.isVisible) {
            tvToolbar.text = title
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            toolbar.setNavigationIcon(R.drawable.back_toolbar)
            toolbar.setNavigationOnClickListener { onBackPressed() }

            pbRegistration.progress = progressBar ?: 0
        }
    }

    private fun setupNavigationController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_main) as NavHostFragment
        val navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> {
                    setupToolbarVisibility(false)
                }

                R.id.personalDataFragment -> {
                    setupToolbarVisibility(
                        true,
                        getString(R.string.title_personal_data),
                        Constants.progressStepOne,
                    )
                }

                R.id.addressFragment -> {
                    setupToolbarVisibility(
                        true,
                        getString(R.string.title_address_data),
                        Constants.progressStepTwo,
                    )
                }

                R.id.reviewDataFragment -> {
                    setupToolbarVisibility(
                        true,
                        getString(R.string.title_review_data),
                        Constants.progressStepThree,
                    )
                }
            }
        }
    }
}
