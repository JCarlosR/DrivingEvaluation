package com.programacionymas.drivingevaluation.drivers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.programacionymas.drivingevaluation.data.DriverRepository
import com.programacionymas.drivingevaluation.domain.Driver

@Composable
fun DriverList(
    contentPadding: PaddingValues = PaddingValues(),
    drivers: List<Driver>,
    onEvaluateDriver: (driver: Driver)->Unit = {}
) {
    LazyColumn(
        contentPadding = contentPadding,
        modifier = Modifier.padding(top = 12.dp)
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