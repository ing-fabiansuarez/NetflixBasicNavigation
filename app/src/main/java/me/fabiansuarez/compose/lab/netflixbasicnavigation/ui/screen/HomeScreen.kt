package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixCategory
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixMovie
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.sampleCategories
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.*
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.viewmodel.HomeViewModel

// ─── HomeScreen (Stateful) ──────────────────────────────────────────────────
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    HomeContent(
        selectedTab = viewModel.selectedTab,
        categories = viewModel.categories,
        onTabSelected = viewModel::onTabSelected
    )
}

// ─── HomeContent (Stateless) ────────────────────────────────────────────────
@Composable
fun HomeContent(
    selectedTab: Int,
    categories: List<NetflixCategory>,
    onTabSelected: (Int) -> Unit
) {
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
            items(categories) { category ->
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
            onTabSelected = onTabSelected
        )

        // ── Bottom navigation bar ────────────────────────────────────────────
        NetflixBottomBar(
            selectedIndex = 0,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(
    name = "Netflix Home Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    NetflixBasicNavigationTheme {
        HomeContent(
            selectedTab = 0,
            categories = sampleCategories,
            onTabSelected = {}
        )
    }
}

// ─── TopBar ──────────────────────────────────────────────────────────────────
@Composable
fun NetflixTopBar(
    onProfileClick: () -> Unit,
    onSearchClick: () -> Unit,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf("Series", "Películas", "Mi lista")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = 0.8f),
                        Color.Black.copy(alpha = 0.4f),
                        Color.Transparent
                    )
                )
            )
            .padding(top = 40.dp, bottom = 12.dp)
            .zIndex(1f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "N",
                color = NetflixRed,
                fontSize = 34.sp,
                fontWeight = FontWeight.Black
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = NetflixWhite,
                        modifier = Modifier.size(28.dp)
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.Blue)
                        .clickable { onProfileClick() }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            tabs.forEachIndexed { index, title ->
                Text(
                    text = title,
                    color = if (selectedTab == index) NetflixWhite else NetflixGray,
                    fontSize = 15.sp,
                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Medium,
                    modifier = Modifier.clickable { onTabSelected(index) }
                )
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
            .height(550.dp)
    ) {
        // Main Image (Placeholder)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        0.5f to Color(0xFF0A1628).copy(alpha = 0.5f),
                        1f to NetflixDark
                    )
                )
        ) {
            // Visual element mimicking a movie poster
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "EL PROBLEMA\nDE LOS 3",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    lineHeight = 44.sp
                )
                Text(
                    text = "CUERPOS",
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 4.sp
                )
            }
        }

        // Content on top of banner
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Genre labels
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Ciencia ficción", color = NetflixWhite, fontSize = 13.sp)
                Text(" • ", color = NetflixRed, fontWeight = FontWeight.Bold)
                Text("Cerebral", color = NetflixWhite, fontSize = 13.sp)
                Text(" • ", color = NetflixRed, fontWeight = FontWeight.Bold)
                Text("Drama TV", color = NetflixWhite, fontSize = 13.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Action buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // My List
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { }
                ) {
                    Icon(Icons.Default.Add, "My List", tint = NetflixWhite)
                    Text("Mi lista", color = NetflixWhite, fontSize = 12.sp)
                }

                // Play Button
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NetflixWhite),
                    modifier = Modifier
                        .height(40.dp)
                        .width(110.dp),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.PlayArrow, null, tint = Color.Black)
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Reproducir", color = Color.Black, fontWeight = FontWeight.Bold)
                    }
                }

                // Info
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { }
                ) {
                    Icon(Icons.Default.Info, "Info", tint = NetflixWhite)
                    Text("Información", color = NetflixWhite, fontSize = 12.sp)
                }
            }
        }
    }
}

// ─── Category Row ────────────────────────────────────────────────────────────
@Composable
fun NetflixCategoryRow(category: NetflixCategory) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = category.title,
            color = NetflixWhite,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(category.movies) { movie ->
                NetflixMovieCard(movie = movie)
            }
        }
    }
}

// ─── Movie Card ──────────────────────────────────────────────────────────────
@Composable
fun NetflixMovieCard(movie: NetflixMovie) {
    Box(
        modifier = Modifier
            .width(115.dp)
            .height(165.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(movie.cardColor)
            .clickable { }
    ) {
        // Simulated Movie content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = movie.title,
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        // Top 10 badge
        if (movie.isTop10) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
                    .size(24.dp)
                    .background(NetflixRed, RoundedCornerShape(2.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "TOP\n10",
                    color = Color.White,
                    fontSize = 7.sp,
                    fontWeight = FontWeight.Black,
                    lineHeight = 8.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        // New release tag
        if (movie.isNew) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .background(NetflixRed)
                    .padding(vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "NUEVO EPISODIO",
                    color = Color.White,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Netflix logo overlay (bottom left)
        Text(
            text = "N",
            color = NetflixRed,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(4.dp)
        )
    }
}

// ─── Bottom Navigation ───────────────────────────────────────────────────────
@Composable
fun NetflixBottomBar(selectedIndex: Int, modifier: Modifier = Modifier) {
    NavigationBar(
        containerColor = Color.Black.copy(alpha = 0.95f),
        tonalElevation = 0.dp,
        modifier = modifier.height(65.dp)
    ) {
        val items = listOf(
            "Inicio" to painterResource(id = android.R.drawable.ic_menu_today),
            "Juegos" to painterResource(id = android.R.drawable.ic_menu_view),
            "Nuevo y popular" to painterResource(id = android.R.drawable.ic_menu_recent_history),
            "Mi Netflix" to painterResource(id = android.R.drawable.ic_menu_myplaces)
        )

        items.forEachIndexed { index, (label, icon) ->
            NavigationBarItem(
                selected = index == selectedIndex,
                onClick = { },
                icon = {
                    Icon(
                        painter = icon,
                        contentDescription = label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(
                        text = label,
                        fontSize = 10.sp
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = NetflixWhite,
                    unselectedIconColor = NetflixGray,
                    selectedTextColor = NetflixWhite,
                    unselectedTextColor = NetflixGray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
