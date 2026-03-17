package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen

import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.NetflixDark


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixCategory
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixMovie
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.sampleCategories
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.*


// ─── HomeScreen ───────────────────────────────────────────────────────────────
@Preview(
    name = "Netflix Home Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixDark)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            // ── Hero Banner ──────────────────────────────────────────────────
            item {
                NetflixHeroBanner()
            }

            // ── Category rows ────────────────────────────────────────────────
            items(sampleCategories) { category ->
                NetflixCategoryRow(category = category)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Extra bottom padding for nav bar
            item { Spacer(modifier = Modifier.height(80.dp)) }
        }

        // ── Top navigation bar ───────────────────────────────────────────────
        NetflixTopBar(
            onProfileClick = { },
            onSearchClick = { },
            selectedTab = selectedTab,
            onTabSelected = { selectedTab = it }
        )

        // ── Bottom navigation bar ────────────────────────────────────────────
        NetflixBottomBar(
            selectedIndex = 0,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

// ─── Top Bar ──────────────────────────────────────────────────────────────────
@Composable
fun NetflixTopBar(
    onProfileClick: () -> Unit,
    onSearchClick: () -> Unit,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xDD000000),
                        Color(0x88000000),
                        Color.Transparent
                    )
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Netflix logo
            Text(
                text = "NETFLIX",
                fontSize = 22.sp,
                fontWeight = FontWeight.Black,
                color = NetflixRed,
                letterSpacing = 1.5.sp,
                modifier = Modifier.weight(1f)
            )

            // Search icon
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Buscar",
                    tint = NetflixWhite,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(4.dp))

            // Profile avatar
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFF46D369))
                    .clickable { onProfileClick() },
                contentAlignment = Alignment.Center
            ) {
                Text("😎", fontSize = 18.sp)
            }
        }

        // ── Tabs row ─────────────────────────────────────────────────────────
        val tabs = listOf("Inicio", "Series", "Películas", "Categorías")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            tabs.forEachIndexed { index, tab ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { onTabSelected(index) }
                        .padding(bottom = 6.dp)
                ) {
                    Text(
                        text = tab,
                        color = if (selectedTab == index) NetflixWhite else NetflixHint,
                        fontSize = 14.sp,
                        fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                    )
                    if (selectedTab == index) {
                        Spacer(modifier = Modifier.height(3.dp))
                        Box(
                            modifier = Modifier
                                .width(20.dp)
                                .height(2.dp)
                                .background(NetflixRed, RoundedCornerShape(1.dp))
                        )
                    }
                }
            }
        }
    }
}

// ─── Hero Banner ─────────────────────────────────────────────────────────────
@Composable
fun NetflixHeroBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(520.dp)
    ) {
        // Background simulated poster
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF0A1628),
                            Color(0xFF1A2A4A),
                            Color(0xFF0A1628)
                        )
                    )
                )
        )

        // Simulated movie still effect
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0x332060CC),
                            Color(0x00000000)
                        ),
                        radius = 600f
                    )
                )
        )

        // Bottom gradient for text readability
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color(0xCC141414),
                            Color(0xFF141414)
                        )
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            // TOP 10 badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(NetflixRed, RoundedCornerShape(2.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = "TOP 10",
                        color = NetflixWhite,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Serie del momento",
                    color = NetflixHint,
                    fontSize = 13.sp
                )
            }

            // Title
            Text(
                text = "El problema\nde los 3 cuerpos",
                fontSize = 32.sp,
                fontWeight = FontWeight.Black,
                color = NetflixWhite,
                lineHeight = 36.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tags
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                listOf("Acción", "Ciencia ficción", "Misterio").forEach { tag ->
                    Text(
                        text = tag,
                        color = NetflixHint,
                        fontSize = 13.sp
                    )
                    if (tag != "Misterio") {
                        Text("·", color = NetflixHint, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // CTA buttons
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                // Play button
                Button(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NetflixWhite,
                        contentColor = NetflixBlack
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Reproducir",
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                }

                // My list button
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .weight(1f)
                        .height(42.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color(0x66333333),
                        contentColor = NetflixWhite
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp, Color(0x66FFFFFF)
                    ),
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Mi lista",
                        fontWeight = FontWeight.Medium,
                        fontSize = 15.sp
                    )
                }
            }
        }

        // Info button top-right
        IconButton(
            onClick = {},
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp, bottom = 16.dp)
                .size(36.dp)
                .border(1.dp, NetflixWhite, CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Más info",
                tint = NetflixWhite,
                modifier = Modifier.size(18.dp)
            )
        }
    }
}

// ─── Category Row ─────────────────────────────────────────────────────────────
@Composable
fun NetflixCategoryRow(category: NetflixCategory) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = category.title,
            color = NetflixWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 12.dp, bottom = 10.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            items(category.movies) { movie ->
                NetflixMovieCard(movie = movie)
            }
        }
    }
}

// ─── Movie Card ───────────────────────────────────────────────────────────────
@Composable
fun NetflixMovieCard(movie: NetflixMovie) {
    Box(
        modifier = Modifier
            .width(110.dp)
            .height(160.dp)
    ) {
        // Poster background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(movie.cardColor, movie.cardColor.copy(alpha = 0.7f))
                    )
                )
        ) {
            // Simulated poster grid lines for texture
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color(0x44000000)
                            )
                        )
                    )
            )

            // Title on card
            Text(
                text = movie.title,
                color = NetflixWhite.copy(alpha = 0.9f),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(6.dp),
                lineHeight = 14.sp
            )
        }

        // NEW badge
        if (movie.isNew) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(4.dp)
                    .background(NetflixRed, RoundedCornerShape(2.dp))
                    .padding(horizontal = 4.dp, vertical = 1.dp)
            ) {
                Text(
                    text = "NUEVO",
                    color = NetflixWhite,
                    fontSize = 9.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        // TOP 10 badge
        if (movie.isTop10 && movie.top10Position != null) {
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .background(NetflixRed, RoundedCornerShape(2.dp))
                        .padding(horizontal = 3.dp, vertical = 1.dp)
                ) {
                    Text(
                        text = "#${movie.top10Position}",
                        color = NetflixWhite,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}

// ─── Bottom Nav Bar ───────────────────────────────────────────────────────────
@Composable
fun NetflixBottomBar(
    selectedIndex: Int,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Pair("Inicio", Icons.Default.Home),
        Pair("Buscar", Icons.Default.Search),
        Pair("Próximamente", Icons.Default.PlayArrow),
        Pair("Descargas", Icons.Default.KeyboardArrowDown),
        Pair("Más", Icons.Default.Menu)
    )

    NavigationBar(
        modifier = modifier,
        containerColor = Color(0xF0141414),
        contentColor = NetflixWhite,
        tonalElevation = 0.dp
    ) {
        items.forEachIndexed { index, (label, icon) ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = {},
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 10.sp,
                        maxLines = 1
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NetflixWhite,
                    unselectedIconColor = NetflixHint,
                    selectedTextColor = NetflixWhite,
                    unselectedTextColor = NetflixHint,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
