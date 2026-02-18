package com.km.feature.detail

import com.km.feature.main.Route
import kotlinx.serialization.Serializable

@Serializable
data class RouteDetail(
    val concertId: String,
) : Route
