package com.hobby_projects.myjcconverter

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun Converter(
    modifier: Modifier,
    onAction: (ConverterAction) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConverterType(iconDrawable = R.drawable.ic_currency, measure = "Currency", onClick = { onAction(ConverterAction.Currency) })
            ConverterType(iconDrawable = R.drawable.ic_length, measure = "Length", onClick = { onAction(ConverterAction.Length) })
            ConverterType(iconDrawable = R.drawable.ic_area, measure = "Area", onClick = { onAction(ConverterAction.Area) })
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConverterType(iconDrawable = R.drawable.ic_volume, measure = "Volume", onClick = { onAction(ConverterAction.Volume) })
            ConverterType(iconDrawable = R.drawable.ic_weight, measure = "Weight", onClick = { onAction(ConverterAction.Weight) })
            ConverterType(iconDrawable = R.drawable.ic_temperature, measure = "Temperature", onClick = { onAction(ConverterAction.Temperature) })
        }

        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConverterType(iconDrawable = R.drawable.ic_speed, measure = "Speed", onClick = { onAction(ConverterAction.Speed) })
            ConverterType(iconDrawable = R.drawable.ic_pressure, measure = "Pressure", onClick = { onAction(ConverterAction.Pressure) })
            ConverterType(iconDrawable = R.drawable.ic_power, measure = "Power", onClick = { onAction(ConverterAction.Power) })
        }
    }
}
