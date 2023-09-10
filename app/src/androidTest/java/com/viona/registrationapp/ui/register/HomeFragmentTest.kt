package com.viona.registrationapp.ui.register

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.viona.registrationapp.MainActivity
import com.viona.registrationapp.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeFragmentTest {
    private lateinit var scenario: FragmentScenario<HomeFragment>

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {
        scenario = launchFragmentInContainer<HomeFragment>(
            null,
            R.style.Theme_RegistrationApp,
        )
        scenario.moveToState(Lifecycle.State.STARTED)
    }

    @Test
    fun showHomeFragmentLaunch() {
        onView(withId(R.id.btn_register)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_register)).check(matches(withText("Register")))
    }
}
