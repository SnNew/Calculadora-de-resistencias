package com.example.calcularresistencias.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val mapaColores = mapOf(
    "Negro" to Color.Black,
    "Marrón" to Color(0xFF8B4513),
    "Rojo" to Color.Red,
    "Naranja" to Color(0xFFFFA500),
    "Amarillo" to Color.Yellow,
    "Verde" to Color.Green,
    "Azul" to Color.Blue,
    "Violeta" to Color(0xFF8A2BE2),
    "Gris" to Color.Gray,
    "Blanco" to Color.White,
    "Dorado" to Color(0xFFFFD700),
    "Plateado" to Color(0xFFC0C0C0),
    "Ninguno" to Color.Transparent
)

@Composable
fun PantallaCalculadora() {
    val coloresBanda12 = listOf(
        "Negro" to 0, "Marrón" to 1, "Rojo" to 2, "Naranja" to 3, "Amarillo" to 4,
        "Verde" to 5, "Azul" to 6, "Violeta" to 7, "Gris" to 8, "Blanco" to 9
    )

    val coloresMultiplicador = listOf(
        "Negro" to 1, "Marrón" to 10, "Rojo" to 100, "Naranja" to 1_000, "Amarillo" to 10_000
    )

    val coloresTolerancia = listOf(
        "Dorado" to "±5%", "Plateado" to "±10%", "Ninguno" to "±20%"
    )

    var banda1 by remember { mutableStateOf("Negro") }
    var banda2 by remember { mutableStateOf("Negro") }
    var banda3 by remember { mutableStateOf("Negro") }
    var tolerancia by remember { mutableStateOf("Dorado") }

    val valor1 = coloresBanda12.find { it.first == banda1 }?.second ?: 0
    val valor2 = coloresBanda12.find { it.first == banda2 }?.second ?: 0
    val multiplicador = coloresMultiplicador.find { it.first == banda3 }?.second ?: 1
    val toleranciaValor = coloresTolerancia.find { it.first == tolerancia }?.second ?: ""

    val resultado = ((valor1 * 10) + valor2) * multiplicador

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = "Calculadora de Resistencias",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Desplegable("Banda 1", coloresBanda12.map { it.first }, banda1) { banda1 = it }
                Desplegable("Banda 2", coloresBanda12.map { it.first }, banda2) { banda2 = it }
                Desplegable("Multiplicador", coloresMultiplicador.map { it.first }, banda3) { banda3 = it }
                Desplegable("Tolerancia", coloresTolerancia.map { it.first }, tolerancia) { tolerancia = it }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        ResistenciaConBandas(banda1, banda2, banda3, tolerancia)

        Spacer(modifier = Modifier.height(12.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Resultado",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$resultado Ω $toleranciaValor",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontSize = 22.sp
                )
            }
        }
    }
}

@Composable
fun ResistenciaConBandas(
    banda1: String,
    banda2: String,
    banda3: String,
    tolerancia: String
) {
    val anchoCuerpo = 200.dp
    val altoCuerpo = 40.dp
    val anchoBanda = 8.dp

    val colorBanda1 = mapaColores[banda1] ?: Color.Transparent
    val colorBanda2 = mapaColores[banda2] ?: Color.Transparent
    val colorBanda3 = mapaColores[banda3] ?: Color.Transparent
    val colorTolerancia = mapaColores[tolerancia] ?: Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = anchoCuerpo, height = altoCuerpo)
                .background(
                    color = Color(0xFFD6D6D6),
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                listOf(colorBanda1, colorBanda2, colorBanda3, colorTolerancia).forEach { color ->
                    Box(
                        modifier = Modifier
                            .width(anchoBanda)
                            .fillMaxHeight()
                            .background(color)
                    )
                }
            }
        }
    }
}
