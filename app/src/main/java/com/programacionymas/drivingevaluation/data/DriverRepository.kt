package com.programacionymas.drivingevaluation.data

import com.programacionymas.drivingevaluation.domain.Driver

object DriverRepository {
    fun getDummyData(): List<Driver> {
        return listOf(
            Driver("0001", "76474871", "DRIVER NAME 1"),
            Driver("0002", "67748417", "DRIVER NAME 2"),
        )
    }
}