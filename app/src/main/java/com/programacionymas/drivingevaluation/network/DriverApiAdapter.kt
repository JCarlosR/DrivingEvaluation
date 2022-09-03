package com.programacionymas.drivingevaluation.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DriverApiAdapter {
    private var API_SERVICE: DriverApiService? = null

    /**
     * Localhost IP for AVD emulators: 10.0.2.2
     */
    private val BASE_URL = "http://grtclalibertad.gob.pe/citas/api/"

    fun getApiService(): DriverApiService {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        if (API_SERVICE == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build()) // <-- set log level
                .build()

            API_SERVICE = retrofit.create(DriverApiService::class.java)
        }

        return API_SERVICE ?: getApiService()
    }
}