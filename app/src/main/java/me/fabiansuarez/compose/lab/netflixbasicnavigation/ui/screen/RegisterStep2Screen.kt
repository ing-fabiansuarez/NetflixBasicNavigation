package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.NetflixPlan
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.netflixPlans
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.*

@Preview(
    name = "Netflix Register Step 2 - Elige tu plan",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RegisterStep2Screen(navController: NavController = rememberNavController()) {
    val scrollState = rememberScrollState()
    var selectedPlan by remember { mutableStateOf(3) } // Premium selected by default

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {

            // ── Top Bar ──────────────────────────────────────────────────────
            RegisterTopBar(
                onBackClick = {
                    navController.popBackStack()
                },
                step = 2,
                totalSteps = 3
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ── Step info ────────────────────────────────────────────────
                Text(
                    text = "PASO 2 DE 3",
                    color = NetflixHint,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Elige tu plan",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = NetflixWhite,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ── Benefits bullets ─────────────────────────────────────────
                PlanBenefitRow("Sin compromisos, cancela cuando quieras.")
                PlanBenefitRow("Todo Netflix. Planes a partir de COP 15.900/mes.")
                PlanBenefitRow("Sin anuncios en los planes Estándar y Premium.")

                Spacer(modifier = Modifier.height(28.dp))

                // ── Plan cards ───────────────────────────────────────────────
                netflixPlans.forEach { plan ->
                    PlanCard(
                        plan = plan,
                        isSelected = selectedPlan == plan.id,
                        onClick = { selectedPlan = plan.id }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))

                Spacer(modifier = Modifier.height(28.dp))

                // ── Continue button ──────────────────────────────────────────
                Button(
                    onClick = {
                        navController.navigate("register_step_3")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NetflixRed,
                        contentColor = NetflixWhite
                    )
                ) {
                    Text(
                        text = "Siguiente",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Los precios incluyen todos los impuestos aplicables.\nAl unirte, aceptas los Términos de uso de Netflix.",
                    color = Color(0xFF666666),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    NetflixLanguageSelector()
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

// ─── Plan Benefit Row ────────────────────────────────────────────────────────
@Composable
fun PlanBenefitRow(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color(0xFF46D369), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                tint = NetflixBlack,
                modifier = Modifier.size(13.dp)
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = text,
            color = NetflixWhite,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

// ─── Plan Card ───────────────────────────────────────────────────────────────
@Composable
fun PlanCard(
    plan: NetflixPlan,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {

        // Badge
        if (plan.badge != null) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .zIndex(1f)
                    .offset(y = (-10).dp)
                    .background(NetflixRed, RoundedCornerShape(2.dp))
                    .padding(horizontal = 10.dp, vertical = 3.dp)
            ) {
                Text(
                    text = plan.badge,
                    color = NetflixWhite,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 0.5.sp
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(
                    if (isSelected) Color(0xFF1A1A2E) else Color(0xFF1A1A1A)
                )
                .border(
                    width = if (isSelected) 2.dp else 1.dp,
                    color = if (isSelected) NetflixWhite else Color(0xFF3A3A3A),
                    shape = RoundedCornerShape(6.dp)
                )
                .clickable { onClick() }
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Selection circle
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .border(
                            2.dp,
                            if (isSelected) NetflixWhite else Color(0xFF555555),
                            CircleShape
                        )
                        .background(
                            if (isSelected) NetflixWhite else Color.Transparent,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(NetflixBlack, CircleShape)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = plan.name,
                        color = NetflixWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp
                    )
                    Text(
                        text = plan.quality,
                        color = NetflixHint,
                        fontSize = 13.sp
                    )
                }

                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = plan.priceMonthly,
                        color = NetflixWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End
                    )
                }
            }
        }
    }
}

// ─── Shared TopBar for Register flow ─────────────────────────────────────────
@Composable
fun RegisterTopBar(
    onBackClick: () -> Unit,
    step: Int,
    totalSteps: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Volver",
                tint = NetflixWhite,
                modifier = Modifier.size(24.dp)
            )
        }

        Text(
            text = "NETFLIX",
            fontSize = 28.sp,
            fontWeight = FontWeight.Black,
            color = NetflixRed,
            letterSpacing = 2.sp,
            modifier = Modifier.align(Alignment.Center)
        )
    }

    NetflixStepProgress(totalSteps = totalSteps, currentStep = step)
}
