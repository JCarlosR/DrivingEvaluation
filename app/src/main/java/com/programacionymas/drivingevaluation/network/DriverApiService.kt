package com.programacionymas.drivingevaluation.network

import com.programacionymas.drivingevaluation.domain.Answer
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.domain.Question
import com.programacionymas.drivingevaluation.network.requests.LoginRequest
import com.programacionymas.drivingevaluation.network.responses.LoginResponse
import com.programacionymas.drivingevaluation.network.responses.SimpleResponse
import retrofit2.Call
import retrofit2.http.*

interface DriverApiService {
    /**
     * Required format for date is: dd/mm/yyyy
     */
    @GET("drivers")
    fun getDrivers(
        @Header("Authorization") authorization: String,
        @Query("date") date: String
    ): Call<ArrayList<Driver>>

    @GET("drivers-test")
    fun getQuestions(
        @Header("Authorization") authorization: String,
    ): Call<ArrayList<Question>>

    @POST("auth/loginApp")
    fun postLogin(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @POST("drivers/8494/evaluation")
    fun postEvaluation(
        @Header("Authorization") authorization: String,
        @Body evaluationBody: ArrayList<Answer>
    ): Call<SimpleResponse>
}