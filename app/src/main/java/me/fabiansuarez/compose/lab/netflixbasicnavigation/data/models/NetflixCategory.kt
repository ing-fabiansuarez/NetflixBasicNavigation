package me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models

data class NetflixCategory(
    val title: String,
    val movies: List<NetflixMovie>
)