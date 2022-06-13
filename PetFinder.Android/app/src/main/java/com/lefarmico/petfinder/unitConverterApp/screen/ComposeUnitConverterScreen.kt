package com.lefarmico.petfinder.unitConverterApp.screen

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.lefarmico.petfinder.R

sealed class ComposeUnitConverterScreen(
    val route: String,
    @StringRes val label: Int,
    @DrawableRes val icon: Int
) {
    companion object {
        val sceens = listOf(
            Temperature,
            Distances
        )

        const val route_temperature = "temperature"
        const val route_distances = "distances"
    }

    private object Temperature : ComposeUnitConverterScreen(
        route_temperature,
        R.string.temperature,
        R.drawable.ic_baseline_thermostat_24
    )

    private object Distances : ComposeUnitConverterScreen(
        route_distances,
        R.string.distances,
        R.drawable.ic_baseline_square_foot_24
    )
}
