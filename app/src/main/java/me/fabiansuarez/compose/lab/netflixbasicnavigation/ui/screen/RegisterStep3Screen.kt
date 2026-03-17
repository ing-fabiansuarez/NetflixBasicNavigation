package me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.fabiansuarez.compose.lab.netflixbasicnavigation.data.models.PaymentMethod
import me.fabiansuarez.compose.lab.netflixbasicnavigation.ui.theme.*



// ─── RegisterStep3Screen - Configurar pago ────────────────────────────────────
@Preview(
    name = "Register Step 3 - PSE",
    showBackground = true,
)
@Composable
fun RegisterStep3Screen() {
    val scrollState = rememberScrollState()
    var selectedPayment by remember { mutableStateOf(PaymentMethod.CARD) }

    // Card fields
    var cardNumber by remember { mutableStateOf("") }
    var cardName by remember { mutableStateOf("") }
    var cardExpiry by remember { mutableStateOf("") }
    var cardCvv by remember { mutableStateOf("") }
    var saveCard by remember { mutableStateOf(true) }

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
                onBackClick = { },
                step = 3,
                totalSteps = 3
            )

            Spacer(modifier = Modifier.height(28.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // ── Step header ──────────────────────────────────────────────
                Text(
                    text = "PASO 3 DE 3",
                    color = NetflixHint,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Configura el pago",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = NetflixWhite,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Tu membresía comienza en cuanto configures tu método de pago.",
                    color = NetflixHint,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ── Selected plan summary ────────────────────────────────────
                SelectedPlanSummary(
                    planName = "Premium",
                    price = "33.908",
                    onChangePlan = {}
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ── Payment method selector ──────────────────────────────────
                Text(
                    text = "Elige cómo pagar",
                    color = NetflixWhite,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 14.dp)
                )

                PaymentMethodSelector(
                    selected = selectedPayment,
                    onSelect = { selectedPayment = it }
                )

                Spacer(modifier = Modifier.height(20.dp))

                // ── Payment form by method ────────────────────────────────────
                when (selectedPayment) {
                    PaymentMethod.CARD -> {
                        CreditCardForm(
                            cardNumber = cardNumber,
                            onCardNumberChange = { cardNumber = it },
                            cardName = cardName,
                            onCardNameChange = { cardName = it },
                            cardExpiry = cardExpiry,
                            onCardExpiryChange = { cardExpiry = it },
                            cardCvv = cardCvv,
                            onCardCvvChange = { cardCvv = it },
                            saveCard = saveCard,
                            onSaveCardChange = { saveCard = it }
                        )
                    }

                    PaymentMethod.PSE -> PseForm()
                    PaymentMethod.NEQUI -> NequiForm()
                    PaymentMethod.EFECTY -> EfectyInfo()
                }

                Spacer(modifier = Modifier.height(28.dp))

                Spacer(modifier = Modifier.height(24.dp))

                // ── Start membership button ──────────────────────────────────
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = NetflixRed,
                        contentColor = NetflixWhite
                    )
                ) {
                    Text(
                        text = "Iniciar membresía",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Al hacer clic en 'Iniciar membresía', aceptas nuestros Términos de uso y confirmas que eres mayor de edad. Netflix se renovará automáticamente en $ 37-908 hasta que canceles.",
                    color = Color(0xFF666666),
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 16.sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                // ── Payment logos ────────────────────────────────────────────
                PaymentLogosRow()

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

// ─── Selected Plan Summary ────────────────────────────────────────────────────
@Composable
fun SelectedPlanSummary(
    planName: String,
    price: String,
    onChangePlan: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(Color(0xFF1A1A1A))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = "Plan seleccionado",
                color = NetflixHint,
                fontSize = 12.sp
            )
            Text(
                text = planName,
                color = NetflixWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = price,
                color = NetflixHint,
                fontSize = 13.sp
            )
        }
        Text(
            text = "Cambiar",
            color = Color(0xFF0071EB),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.clickable { onChangePlan() }
        )
    }
}

// ─── Payment Method Selector ──────────────────────────────────────────────────
@Composable
fun PaymentMethodSelector(
    selected: PaymentMethod,
    onSelect: (PaymentMethod) -> Unit
) {
    val methods = listOf(
        Triple(PaymentMethod.CARD, Icons.Default.AccountCircle, "Tarjeta"),
        Triple(PaymentMethod.PSE, Icons.Default.AccountCircle, "PSE"),
        Triple(PaymentMethod.NEQUI, Icons.Default.Phone, "Nequi"),
        Triple(PaymentMethod.EFECTY, Icons.Default.ShoppingCart, "Efecty")
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        methods.forEach { (method, icon, label) ->
            val isSelected = selected == method
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(
                        if (isSelected) Color(0xFF1A1A2E) else Color(0xFF1A1A1A)
                    )
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        color = if (isSelected) NetflixWhite else Color(0xFF3A3A3A),
                        shape = RoundedCornerShape(6.dp)
                    )
                    .clickable { onSelect(method) }
                    .padding(vertical = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = if (isSelected) NetflixWhite else NetflixHint,
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = label,
                    color = if (isSelected) NetflixWhite else NetflixHint,
                    fontSize = 11.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

// ─── Credit Card Form ─────────────────────────────────────────────────────────
@Composable
fun CreditCardForm(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    cardName: String,
    onCardNameChange: (String) -> Unit,
    cardExpiry: String,
    onCardExpiryChange: (String) -> Unit,
    cardCvv: String,
    onCardCvvChange: (String) -> Unit,
    saveCard: Boolean,
    onSaveCardChange: (Boolean) -> Unit
) {
    // Visual card preview
    CreditCardVisual(
        cardNumber = cardNumber,
        cardName = cardName,
        cardExpiry = cardExpiry
    )

    Spacer(modifier = Modifier.height(20.dp))




}

// ─── Visual Credit Card ───────────────────────────────────────────────────────
@Composable
fun CreditCardVisual(
    cardNumber: String,
    cardName: String,
    cardExpiry: String
) {
    val displayNumber = cardNumber
        .padEnd(16, '•')
        .chunked(4)
        .joinToString("  ")
        .take(22)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(
                androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(Color(0xFF1A237E), Color(0xFF6A1B9A))
                )
            )
            .padding(24.dp)
    ) {
        // Chip
        Box(
            modifier = Modifier
                .size(40.dp, 28.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFFFD700))
                .align(Alignment.TopStart)
                .offset(y = 20.dp)
        )

        // Network logo placeholder
        Text(
            text = "VISA",
            color = NetflixWhite,
            fontSize = 20.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        // Card number
        Text(
            text = displayNumber.ifEmpty { "••••  ••••  ••••  ••••" },
            color = NetflixWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 2.sp,
            modifier = Modifier.align(Alignment.Center)
        )

        // Bottom info
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "TITULAR",
                    color = NetflixWhite.copy(alpha = 0.6f),
                    fontSize = 9.sp,
                    letterSpacing = 1.sp
                )
                Text(
                    text = cardName.uppercase().ifEmpty { "NOMBRE COMPLETO" },
                    color = NetflixWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "VENCE",
                    color = NetflixWhite.copy(alpha = 0.6f),
                    fontSize = 9.sp,
                    letterSpacing = 1.sp
                )
                Text(
                    text = cardExpiry.ifEmpty { "MM/AA" },
                    color = NetflixWhite,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// ─── PSE Form ─────────────────────────────────────────────────────────────────
@Composable
fun PseForm() {
    var bank by remember { mutableStateOf("") }
    var docType by remember { mutableStateOf("CC") }
    var docNumber by remember { mutableStateOf("") }
    var personType by remember { mutableStateOf("Natural") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Transferencia bancaria PSE",
            color = NetflixWhite,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))

        PseDropdownField(
            label = "Banco",
            value = bank,
            placeholder = "Selecciona tu banco",
            options = listOf(
                "Bancolombia",
                "Banco de Bogotá",
                "Davivienda",
                "BBVA",
                "Scotiabank Colpatria",
                "Nequi",
                "Nubank"
            )
        ) { bank = it }

        Spacer(modifier = Modifier.height(12.dp))

        // Document type + number
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(modifier = Modifier.weight(0.35f)) {
                PseDropdownField(
                    label = "Tipo",
                    value = docType,
                    placeholder = "CC",
                    options = listOf("CC", "CE", "NIT", "PP")
                ) { docType = it }
            }
            Box(modifier = Modifier.weight(0.65f)) {
                NetflixTextField(
                    value = docNumber,
                    onValueChange = { docNumber = it },
                    label = "Número de documento",
                    keyboardType = KeyboardType.Number
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Person type
        Text(
            text = "Tipo de persona",
            color = NetflixHint,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            listOf("Natural", "Jurídica").forEach { type ->
                Row(
                    modifier = Modifier.clickable { personType = type },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = personType == type,
                        onClick = { personType = type },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = NetflixWhite,
                            unselectedColor = NetflixGray
                        )
                    )
                    Text(text = type, color = NetflixWhite, fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        InfoBox(text = "Serás redirigido al sitio web de tu banco para completar el pago de forma segura.")
    }
}

// ─── Nequi Form ───────────────────────────────────────────────────────────────
@Composable
fun NequiForm() {
    var phone by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF2D0048))
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF8C00FF)),
                contentAlignment = Alignment.Center
            ) {
                Text("N", color = NetflixWhite, fontWeight = FontWeight.Black, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Pagar con Nequi",
                    color = NetflixWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Recibe una notificación en tu app",
                    color = Color(0xFFBB86FC),
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        NetflixTextField(
            value = phone,
            onValueChange = { phone = it },
            label = "Número de celular Nequi",
            keyboardType = KeyboardType.Phone
        )

        Spacer(modifier = Modifier.height(12.dp))

        InfoBox(text = "Recibirás una notificación push en tu app de Nequi para confirmar el pago.")
    }
}

// ─── Efecty Info ─────────────────────────────────────────────────────────────
@Composable
fun EfectyInfo() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(6.dp))
                .background(Color(0xFF1A1500))
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFFFCC00)),
                contentAlignment = Alignment.Center
            ) {
                Text("E", color = NetflixBlack, fontWeight = FontWeight.Black, fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = "Pagar en Efecty",
                    color = NetflixWhite,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Paga en efectivo en cualquier punto",
                    color = NetflixHint,
                    fontSize = 13.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        listOf(
            "Selecciona este método y haz clic en 'Iniciar membresía'.",
            "Recibirás un código de pago por correo electrónico.",
            "Ve a cualquier punto Efecty o Baloto en Colombia.",
            "Presenta tu código y realiza el pago en efectivo.",
            "Tu cuenta se activará al recibir la confirmación."
        ).forEachIndexed { i, step ->
            Row(
                modifier = Modifier.padding(vertical = 6.dp),
                verticalAlignment = Alignment.Top
            ) {
                Box(
                    modifier = Modifier
                        .size(22.dp)
                        .background(
                            Color(0xFF2A2A2A),
                            androidx.compose.foundation.shape.CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${i + 1}",
                        color = NetflixWhite,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = step,
                    color = NetflixHint,
                    fontSize = 13.sp,
                    lineHeight = 19.sp
                )
            }
        }
    }
}

// ─── PSE Dropdown Field (simulated) ──────────────────────────────────────────
@Composable
fun PseDropdownField(
    label: String,
    value: String,
    placeholder: String,
    options: List<String>,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text(label, color = NetflixHint, fontSize = 14.sp) },
            placeholder = { Text(placeholder, color = NetflixHint, fontSize = 14.sp) },
            trailingIcon = {
                Icon(
                    imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = NetflixHint,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            shape = RoundedCornerShape(4.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = NetflixWhite,
                unfocusedTextColor = NetflixWhite,
                focusedContainerColor = NetflixInput,
                unfocusedContainerColor = NetflixInput,
                focusedBorderColor = NetflixHint,
                unfocusedBorderColor = NetflixBorder
            )
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color(0xFF2A2A2A))
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            color = NetflixWhite,
                            fontSize = 14.sp
                        )
                    },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

// ─── Info Box ─────────────────────────────────────────────────────────────────
@Composable
fun InfoBox(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(Color(0xFF1A1A1A))
            .padding(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = NetflixHint,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            color = NetflixHint,
            fontSize = 12.sp,
            lineHeight = 17.sp
        )
    }
}

// ─── Payment Logos Row ────────────────────────────────────────────────────────
@Composable
fun PaymentLogosRow() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Métodos de pago seguros",
            color = Color(0xFF555555),
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("VISA", "MC", "AMEX", "PSE", "Nequi", "Efecty").forEach { logo ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(3.dp))
                        .background(Color(0xFF1A1A1A))
                        .border(1.dp, Color(0xFF2A2A2A), RoundedCornerShape(3.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = logo,
                        color = Color(0xFF888888),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

