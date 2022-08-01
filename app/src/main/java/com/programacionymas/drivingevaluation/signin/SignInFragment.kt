package com.programacionymas.drivingevaluation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.ui.Screen
import com.programacionymas.drivingevaluation.ui.navigate
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

/**
 * Fragment containing the sign in UI.
 */
class SignInFragment : Fragment() {

    private val viewModel: SignInViewModel by viewModels { SignInViewModelFactory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.navigateTo.observe(viewLifecycleOwner) { navigateToEvent ->
            navigateToEvent.getContentIfNotHandled()?.let { navigateTo ->
                navigate(navigateTo, Screen.SignIn)
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
                    SignIn(
                        onNavigationEvent = { event: SignInEvent ->
                            when (event) {
                                is SignInEvent.SignIn -> {
                                    viewModel.signIn(event.email, event.password)
                                }
                                SignInEvent.SignUp -> {
                                    viewModel.signUp()
                                }
                                SignInEvent.NavigateBack -> {
                                    activity?.onBackPressedDispatcher?.onBackPressed()
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}