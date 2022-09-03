package com.programacionymas.drivingevaluation.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme
import com.programacionymas.drivingevaluation.ui.Screen
import com.programacionymas.drivingevaluation.ui.navigate

class DriversFragment : Fragment() {

    private val viewModel: DriversViewModel by viewModels { DriversViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.DriversList)
            }
        }

        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.sign_in_fragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setContent {
                DrivingEvaluationTheme {
                    DriversScreen(viewModel, onNavigationEvent = { driversEvent ->
                        when (driversEvent) {
                            is DriversScreenEvent.Evaluate -> {
                                evaluate(driversEvent.driver)
                            }
                        }
                    })
                }
            }
        }
    }

    private fun evaluate(driver: Driver) {
        Toast.makeText(context, "Evaluate driver ${driver.fullName}", Toast.LENGTH_LONG).show()
        viewModel.evaluate(driver)
    }

}