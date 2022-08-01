package com.programacionymas.drivingevaluation.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.fragment.app.Fragment
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

class DriversFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.sign_in_fragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContent {
                DrivingEvaluationTheme {
                    DriversScreen(onNavigationEvent = { driversEvent ->
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
    }

}