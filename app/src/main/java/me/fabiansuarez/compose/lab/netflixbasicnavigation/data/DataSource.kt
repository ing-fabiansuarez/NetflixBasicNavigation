package me.fabiansuarez.compose.lab.netflixbasicnavigation.data

import androidx.compose.ui.graphics.Color
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixCategory
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixMovie
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixPlan

// ─── Sample Data ──────────────────────────────────────────────────────────────
val sampleCategories = listOf(
    NetflixCategory(
        title = "Tendencias ahora",
        movies = listOf(
            NetflixMovie(
                1,
                "El problema de los 3 cuerpos",
                "Ciencia ficción",
                "2024",
                "TV-MA",
                Color(0xFF0A1628),
                isTop10 = true,
                top10Position = 1
            ),
            NetflixMovie(
                2,
                "Stranger Things",
                "Drama / Terror",
                "2016",
                "TV-14",
                Color(0xFF1A0A2E),
                isTop10 = true,
                top10Position = 2
            ),
            NetflixMovie(
                3,
                "Squid Game 3",
                "Thriller",
                "2025",
                "TV-MA",
                Color(0xFF2E0A0A),
                isTop10 = true,
                top10Position = 3
            ),
            NetflixMovie(
                4,
                "Wednesday",
                "Comedia oscura",
                "2022",
                "TV-14",
                Color(0xFF0D0D0D),
                isTop10 = true,
                top10Position = 4
            ),
            NetflixMovie(
                5,
                "Bridgerton T4",
                "Romance",
                "2025",
                "TV-14",
                Color(0xFF1A1025),
                isTop10 = true,
                top10Position = 5
            ),
            NetflixMovie(6, "The Witcher", "Fantasía", "2019", "TV-MA", Color(0xFF0A1A0A)),
        )
    ),
    NetflixCategory(
        title = "Nuevos en Netflix",
        movies = listOf(
            NetflixMovie(
                7,
                "Alerta Cobra: Origins",
                "Acción",
                "2026",
                "TV-14",
                Color(0xFF1C1003),
                isNew = true
            ),
            NetflixMovie(
                8,
                "Midnight in Seoul",
                "Romance",
                "2026",
                "TV-MA",
                Color(0xFF030D1C),
                isNew = true
            ),
            NetflixMovie(
                9,
                "Depredador: La leyenda",
                "Acción",
                "2026",
                "R",
                Color(0xFF1C0A03),
                isNew = true
            ),
            NetflixMovie(
                10,
                "Arcane T3",
                "Animación",
                "2026",
                "TV-14",
                Color(0xFF100328),
                isNew = true
            ),
            NetflixMovie(
                11,
                "Black Mirror T7",
                "Ciencia ficción",
                "2026",
                "TV-MA",
                Color(0xFF0A0A0A),
                isNew = true
            ),
        )
    ),
    NetflixCategory(
        title = "Porque viste Stranger Things",
        movies = listOf(
            NetflixMovie(12, "Dark", "Ciencia ficción", "2017", "TV-MA", Color(0xFF1A1203)),
            NetflixMovie(13, "The OA", "Drama / Misterio", "2016", "TV-14", Color(0xFF03101A)),
            NetflixMovie(
                14,
                "Haunting of Hill House",
                "Terror",
                "2018",
                "TV-MA",
                Color(0xFF0D0308)
            ),
            NetflixMovie(15, "Locke & Key", "Fantasía", "2020", "TV-14", Color(0xFF031003)),
            NetflixMovie(16, "Archive 81", "Terror", "2022", "TV-MA", Color(0xFF100A03)),
        )
    ),
    NetflixCategory(
        title = "Películas de acción",
        movies = listOf(
            NetflixMovie(17, "Extraction 3", "Acción", "2026", "R", Color(0xFF1A0A03)),
            NetflixMovie(18, "Red Notice 2", "Thriller", "2025", "PG-13", Color(0xFF1A0003)),
            NetflixMovie(19, "The Gray Man 2", "Espionaje", "2025", "PG-13", Color(0xFF03061A)),
            NetflixMovie(
                20,
                "Army of the Dead 2",
                "Acción / Terror",
                "2025",
                "R",
                Color(0xFF0A1A03)
            ),
        )
    )
)



val netflixPlans = listOf(
    NetflixPlan(
        id = 1,
        name = "Estándar con\nanuncios",
        priceMonthly = "COP 15.900/mes",
        quality = "Buena",
        resolution = "1080p (Full HD)",
        screens = "2",
        downloads = "No disponible",
        spatialAudio = false,
        color = Color(0xFF1565C0),
        badge = null
    ),
    NetflixPlan(
        id = 2,
        name = "Estándar",
        priceMonthly = "COP 23.900/mes",
        quality = "Genial",
        resolution = "1080p (Full HD)",
        screens = "2",
        downloads = "2 dispositivos",
        spatialAudio = false,
        color = Color(0xFF1A237E)
    ),
    NetflixPlan(
        id = 3,
        name = "Premium",
        priceMonthly = "COP 31.900/mes",
        quality = "Mejor",
        resolution = "4K (Ultra HD) + HDR",
        screens = "4",
        downloads = "6 dispositivos",
        spatialAudio = true,
        color = Color(0xFF6A0DAD),
        badge = "MÁS POPULAR"
    )
)