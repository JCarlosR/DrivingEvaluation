package com.programacionymas.drivingevaluation.drivers

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.programacionymas.drivingevaluation.domain.Driver

@Composable
fun DriverList(drivers: List<Driver>) {
    LazyColumn {
        items(drivers) { driver ->
           DriverCard(driver = driver)
        }
    }
}

@Preview
@Composable
fun DriverListPreview() {
    val dummyData = listOf(
        Driver("0001", "76474871", "DRIVER NAME 1"),
        Driver("0002", "67748417", "DRIVER NAME 2"),
    )

    DriverList(drivers = dummyData)
}