package com.example.movieapp.ui.navigation

import kotlinx.serialization.Serializable

/** Route type-safe: tidak ada lagi string "detail/{id}" yang rawan typo. */
@Serializable
data object DashboardRoute

@Serializable
data class DetailRoute(val movieId: Int)
