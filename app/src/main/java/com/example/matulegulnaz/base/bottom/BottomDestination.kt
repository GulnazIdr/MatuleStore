package com.example.matulegulnaz.base.bottom

import kotlinx.serialization.Serializable

interface BottomDestination

@Serializable
data object MainPage: BottomDestination

@Serializable
data object Favorite: BottomDestination