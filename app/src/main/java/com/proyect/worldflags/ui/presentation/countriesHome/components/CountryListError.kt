package com.proyect.worldflags.ui.presentation.countriesHome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.proyect.worldflags.R

@Composable
fun CountryListError(
    error: String,
    refresh: () -> Unit
){
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        when(error){
            "Error loading countries"  -> {
                AsyncImage(
                    model = R.drawable.error_sleeping_1,
                    contentDescription = null,
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .fillMaxWidth(),
                    alignment = Alignment.Center,
                )
                Text(
                    text = "Esto está tomando mucho tiempo...",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { refresh() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Reintentar",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            "No internet connection and no local data available"  -> {
                AsyncImage(
                    model = R.drawable.error_lamp_robot_1,
                    contentDescription = null,
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .fillMaxWidth(),
                    alignment = Alignment.Center,
                )
                Text(
                    text = "Parece que no tienes conexión a internet",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

    }
}