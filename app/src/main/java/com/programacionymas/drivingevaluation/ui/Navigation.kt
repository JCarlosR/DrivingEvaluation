package com.programacionymas.drivingevaluation.ui

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.programacionymas.drivingevaluation.R
import java.security.InvalidParameterException

enum class Screen { SignIn, DriversList, Test }

fun Fragment.navigate(to: Screen, from: Screen) {
    if (to == from) {
        throw InvalidParameterException("Can't navigate to $to")
    }
    when (to) {
        Screen.SignIn -> {
            findNavController().navigate(R.id.sign_in_fragment)
        }
        Screen.DriversList -> {
            findNavController().navigate(R.id.drivers_fragment)
        }
        Screen.Test -> {
            findNavController().navigate(R.id.test_fragment)
        }
    }
}