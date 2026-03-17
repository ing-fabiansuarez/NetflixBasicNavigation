package me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models

import androidx.compose.ui.graphics.Color

// ─── Data models ─────────────────────────────────────────────────────────────
data class NetflixMovie(
    val id: Int,
    val title: String,
    val genre: String,
    val year: String,
    val rating: String,
    val cardColor: Color,   // simulates poster color
    val isNew: Boolean = false,
    val isTop10: Boolean = false,
    val top10Position: Int? = null
)