package me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models

import androidx.compose.ui.graphics.Color

// ─── Plan Data Model ──────────────────────────────────────────────────────────
data class NetflixPlan(
    val id: Int,
    val name: String,
    val priceMonthly: String,
    val quality: String,
    val resolution: String,
    val screens: String,
    val downloads: String,
    val spatialAudio: Boolean,
    val color: Color,
    val badge: String? = null
)