package com.programacionymas.drivingevaluation.data

import com.programacionymas.drivingevaluation.domain.Question
import com.programacionymas.drivingevaluation.network.DriverApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object QuestionRepository {
    var questions: ArrayList<Question> = ArrayList()

    fun fetchQuestions(callback: ()->(Unit)) {
        val accessToken = (UserRepository.user as User.LoggedInUser).accessToken
        val authorizationHeader = "Bearer $accessToken"

        DriverApiAdapter.getApiService().getQuestions(
            authorizationHeader
        ).enqueue(object : Callback<ArrayList<Question>> {
            override fun onResponse(
                call: Call<ArrayList<Question>>,
                response: Response<ArrayList<Question>>
            ) {
                questions = if (response.isSuccessful) {
                    response.body() ?: ArrayList()
                } else {
                    ArrayList()
                }

                callback()
            }

            override fun onFailure(call: Call<ArrayList<Question>>, t: Throwable) {
                questions = ArrayList()
            }
        })
    }

    fun getDummyData(): List<Question> {
        return listOf(
            Question(1, "Is the driver able to do ABC?", "Very important (4 pts)"),
            Question(2, "Is the driver able to do CYZ?", "Less important (2 pts)"),
        )
    }
}