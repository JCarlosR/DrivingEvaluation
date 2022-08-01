package com.programacionymas.drivingevaluation.drivers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.programacionymas.drivingevaluation.data.DriverRepository
import com.programacionymas.drivingevaluation.domain.Driver

@Composable
fun DriverList(
    contentPadding: PaddingValues = PaddingValues(),
    drivers: List<Driver>,
    onEvaluateDriver: (driver: Driver)->Unit = {}
) {
    LazyColumn(
        contentPadding = contentPadding
    ) {
        items(drivers) { driver ->
           DriverCard(
               driver = driver,
               onEvaluateDriver = onEvaluateDriver
           )
        }
    }
}

@Preview
@Composable
fun DriverListPreview() {
    DriverList(drivers = DriverRepository.getDummyData())
}