package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.*

// ─── RegisterScreen ───────────────────────────────────────────────────────────
@Preview(
    name = "Netflix Register Screen",
    showBackground = true,
    showSystemUi = true
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController = rememberNavController()) {
    val scrollState = rememberScrollState()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var promoEmails by remember { mutableStateOf(false) }

    // Step indicator state (Netflix has a multi-step signup)
    val currentStep = 1

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

            // ── Top App Bar ──────────────────────────────────────────────────
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            ) {
                // Back arrow
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = NetflixWhite,
                        modifier = Modifier.size(24.dp)
                    )
                }

                // Netflix logo
                Text(
                    text = "NETFLIX",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    color = NetflixRed,
                    letterSpacing = 2.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // ── Step progress bar ────────────────────────────────────────────
            NetflixStepProgress(
                totalSteps = 3,
                currentStep = currentStep
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // ── Step label ───────────────────────────────────────────────
                Text(
                    text = "PASO ${currentStep} DE 3",
                    color = NetflixHint,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Crea tu contraseña",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = NetflixWhite,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Solo unos pasos más y podrás disfrutar del contenido. No necesitamos tarjeta de crédito.",
                    color = NetflixHint,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ── Email field ──────────────────────────────────────────────
                NetflixTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electrónico",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ── Password field ───────────────────────────────────────────
                NetflixTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Crea una contraseña",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ── Confirm Password field ───────────────────────────────────
                NetflixTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = "Confirma tu contraseña",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisible = confirmPasswordVisible,
                    onPasswordToggle = { confirmPasswordVisible = !confirmPasswordVisible }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // ── Password requirements ────────────────────────────────────
                PasswordRequirementsCard()

                Spacer(modifier = Modifier.height(20.dp))

                // ── Promo emails checkbox ────────────────────────────────────
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { promoEmails = !promoEmails },
                    verticalAlignment = Alignment.Top
                ) {
                    Checkbox(
                        checked = promoEmails,
                        onCheckedChange = { promoEmails = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = NetflixWhite,
                            uncheckedColor = NetflixGray,
                            checkmarkColor = NetflixBlack
                        ),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Me gustaría recibir correos de Netflix con ofertas especiales, novedades y más sobre los productos y servicios de Netflix.",
                        color = NetflixHint,
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ── Continue button ──────────────────────────────────────────
                Button(
                    onClick = {
                        navController.navigate("register_step_2")
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

                // ── Already have account ─────────────────────────────────────
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "¿Ya tienes cuenta? ",
                        color = NetflixHint,
                        fontSize = 14.sp
                    )
                    Text(
                        text = "Inicia sesión.",
                        color = NetflixWhite,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { }
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // ── Legal text ───────────────────────────────────────────────
                Text(
                    text = "Al hacer clic en 'Siguiente', aceptas nuestros Términos de uso y confirmas que has leído nuestra Declaración de privacidad.",
                    color = Color(0xFF666666),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 17.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

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

// ─── Step Progress ────────────────────────────────────────────────────────────
@Composable
fun NetflixStepProgress(totalSteps: Int, currentStep: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
    ) {
        repeat(totalSteps) { index ->
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(
                        if (index < currentStep) NetflixWhite
                        else Color(0xFF404040)
                    )
            )
            if (index < totalSteps - 1) {
                Spacer(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .background(NetflixBlack)
                )
            }
        }
    }
}

// ─── Password Requirements ────────────────────────────────────────────────────
@Composable
fun PasswordRequirementsCard() {
    val requirements = listOf(
        "Entre 8 y 60 caracteres",
        "Al menos una letra",
        "Al menos un número o símbolo"
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF1A1A1A))
            .padding(14.dp)
    ) {
        Text(
            text = "La contraseña debe tener:",
            color = NetflixHint,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        requirements.forEach { req ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 3.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color(0xFF46D369),
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = req,
                    color = NetflixHint,
                    fontSize = 13.sp
                )
            }
        }
    }
}

