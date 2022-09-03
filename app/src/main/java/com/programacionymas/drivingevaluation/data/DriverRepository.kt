package com.programacionymas.drivingevaluation.data

import androidx.lifecycle.MutableLiveData
import com.programacionymas.drivingevaluation.domain.Driver
import com.programacionymas.drivingevaluation.network.DriverApiAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object DriverRepository {
    var driversLiveData: MutableLiveData<ArrayList<Driver>> = MutableLiveData()

    fun fetchDrivers() {
        val accessToken = (UserRepository.user as User.LoggedInUser).accessToken
        val authorizationHeader = "Bearer ${accessToken}"

        DriverApiAdapter.getApiService().getDrivers(
            authorizationHeader,
            "22/08/2022"
        ).enqueue(object :Callback<ArrayList<Driver>> {
            override fun onResponse(
                call: Call<ArrayList<Driver>>,
                response: Response<ArrayList<Driver>>
            ) {
                if (response.isSuccessful) {
                    driversLiveData.postValue(response.body())
                } else {
                    driversLiveData.postValue(ArrayList())
                }
            }

            override fun onFailure(call: Call<ArrayList<Driver>>, t: Throwable) {
                driversLiveData.postValue(ArrayList())
            }
        })
    }

    fun getDummyData(): ArrayList<Driver> {
        return arrayListOf(
            Driver("111", "0001", "76474871", "DRIVER NAME 1"),
            Driver("222", "0002", "67748417", "DRIVER NAME 2"),
        )
    }
}