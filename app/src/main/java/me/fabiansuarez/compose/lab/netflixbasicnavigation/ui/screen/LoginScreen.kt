package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.*


// ─── LoginScreen ─────────────────────────────────────────────────────────────
@Preview(
    name = "Netflix Login Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(NetflixBlack)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x99000000),
                            Color(0xCC000000),
                            Color(0xFF000000)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // ── Top bar ──────────────────────────────────────────────────────
            Spacer(modifier = Modifier.height(52.dp))


            Text(
                text = "NETFLIX",
                fontSize = 40.sp,
                fontWeight = FontWeight.Black,
                color = NetflixRed,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // ── Card container ───────────────────────────────────────────────
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xCC000000))
                    .padding(horizontal = 20.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Iniciar sesión",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = NetflixWhite,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )

                // ── Email field ──────────────────────────────────────────────
                NetflixTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Correo electrónico o número de teléfono",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                // ── Password field ───────────────────────────────────────────
                NetflixTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = "Contraseña",
                    keyboardType = KeyboardType.Password,
                    isPassword = true,
                    passwordVisible = passwordVisible,
                    onPasswordToggle = { passwordVisible = !passwordVisible }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ── Sign in button ───────────────────────────────────────────
                Button(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NetflixRed,
                        contentColor = NetflixWhite
                    )
                ) {
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ── OR divider ───────────────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = NetflixBorder,
                        thickness = 1.dp
                    )
                    Text(
                        text = "  O  ",
                        color = NetflixGray,
                        fontSize = 13.sp
                    )
                    Divider(
                        modifier = Modifier.weight(1f),
                        color = NetflixBorder,
                        thickness = 1.dp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // ── Use access code button ───────────────────────────────────
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = NetflixWhite
                    ),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp, NetflixBorder
                    )
                ) {
                    Text(
                        text = "Usar un código de acceso",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ── Forgot password ──────────────────────────────────────────
                Text(
                    text = "¿Olvidaste la contraseña?",
                    color = NetflixWhite,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {}
                )

                Spacer(modifier = Modifier.height(32.dp))

                // ── Remember me checkbox ─────────────────────────────────────
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = NetflixWhite,
                            uncheckedColor = NetflixGray,
                            checkmarkColor = NetflixBlack
                        ),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Recuérdame",
                        color = NetflixHint,
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ── Register row ─────────────────────────────────────────────
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "¿Eres nuevo en Netflix? ",
                        color = NetflixHint,
                        fontSize = 15.sp
                    )
                    Text(
                        text = "Suscríbete ahora.",
                        color = NetflixWhite,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // ── reCAPTCHA notice ─────────────────────────────────────────────
            Text(
                text = "Esta página está protegida por Google reCAPTCHA para confirmar que no eres un robot.",
                color = NetflixHint,
                fontSize = 13.sp,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Más información",
                color = Color(0xFF0071EB),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ── Footer language ──────────────────────────────────────────────
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                NetflixLanguageSelector()
            }
        }
    }
}

// ─── Reusable TextField ───────────────────────────────────────────────────────
@Composable
fun NetflixTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onPasswordToggle: (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = NetflixHint,
                fontSize = 14.sp
            )
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = NetflixWhite,
            unfocusedTextColor = NetflixWhite,
            focusedContainerColor = NetflixInput,
            unfocusedContainerColor = NetflixInput,
            focusedBorderColor = NetflixHint,
            unfocusedBorderColor = NetflixBorder,
            cursorColor = NetflixWhite,
            focusedLabelColor = NetflixHint,
            unfocusedLabelColor = NetflixHint
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword && !passwordVisible)
            PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = if (isPassword && onPasswordToggle != null) {
            {
                Text(
                    text = if (passwordVisible) "OCULTAR" else "MOSTRAR",
                    color = NetflixHint,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { onPasswordToggle() }
                )
            }
        } else null
    )
}

// ─── Language Selector ────────────────────────────────────────────────────────
@Composable
fun NetflixLanguageSelector() {
    Row(
        modifier = Modifier
            .border(1.dp, NetflixBorder, RoundedCornerShape(2.dp))
            .padding(horizontal = 12.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text("🌐", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "Español",
            color = NetflixHint,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text("▾", color = NetflixHint, fontSize = 12.sp)
    }
}

