package com.programacionymas.drivingevaluation.evaluation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.programacionymas.drivingevaluation.R
import com.programacionymas.drivingevaluation.data.EvaluationRepository
import com.programacionymas.drivingevaluation.data.User
import com.programacionymas.drivingevaluation.data.UserRepository
import com.programacionymas.drivingevaluation.domain.Answer
import com.programacionymas.drivingevaluation.network.DriverApiAdapter
import com.programacionymas.drivingevaluation.network.responses.SimpleResponse
import com.programacionymas.drivingevaluation.theme.DrivingEvaluationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                                goBack()
                            }
                        }
                    })
                }
            }
        }
    }

    private fun submit(answers: ArrayList<Answer>) {
        answers.forEach {
            Log.d(TAG, it.toString())
        }

        val driverId = EvaluationRepository.driver?.id ?: return

        val accessToken = (UserRepository.user as User.LoggedInUser).accessToken
        val authorizationHeader = "Bearer $accessToken"

        DriverApiAdapter.getApiService()
            .postEvaluation(authorizationHeader, driverId, answers)
            .enqueue(object : Callback<SimpleResponse> {
                override fun onResponse(
                    call: Call<SimpleResponse>,
                    response: Response<SimpleResponse>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(context, getString(R.string.submitted_test, answers.size), Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, getString(R.string.error_submitting_test), Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<SimpleResponse>, t: Throwable) {
                    Toast.makeText(context, t.localizedMessage, Toast.LENGTH_LONG)
                        .show()
                }
            })
    }

    private fun goBack() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    companion object {
        private const val TAG = "EvaluationFragment"
    }

}