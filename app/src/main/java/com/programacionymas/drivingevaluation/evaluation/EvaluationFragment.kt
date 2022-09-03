package com.programacionymas.drivingevaluation.evaluation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.domain.Answer
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme

class EvaluationFragment : Fragment() {

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
                    TestScreen(onNavigationEvent = { testEvent ->
                        when (testEvent) {
                            is TestScreenEvent.SubmitTest -> {
                                submit(testEvent.answers)
                            }
                            TestScreenEvent.NavigateBack -> {
                                activity?.onBackPressedDispatcher?.onBackPressed()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun submit(answers: List<Answer>) {
        Toast.makeText(context, "Submit test with ${answers.size} answers", Toast.LENGTH_LONG)
            .show()
    }

}