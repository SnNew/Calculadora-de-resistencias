package com.example.calcularresistencias

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.calcularresistencias.items.PantallaCalculadora
import com.example.calcularresistencias.ui.theme.CalcularResistenciasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcularResistenciasTheme {
                PantallaCalculadora()
            }
        }
    }
}
