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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.proyect.worldflags.R

@Composable
fun CountryListError(
    error: String,
    refresh: () -> Unit
) {
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

        when(error){
            context.getString(R.string.error_loading_countries) -> {
                AsyncImage(
                    model = R.drawable.error_sleeping_1,
                    contentDescription = null,
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .fillMaxWidth(),
                    alignment = Alignment.Center,
                )
                Text(
                    text = context.getString(R.string.taking_too_long),
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
                        text = context.getString(R.string.retry),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            context.getString(R.string.no_internet_no_data) -> {
                AsyncImage(
                    model = R.drawable.error_lamp_robot_1,
                    contentDescription = null,
                    filterQuality = FilterQuality.High,
                    modifier = Modifier
                        .fillMaxWidth(),
                    alignment = Alignment.Center,
                )
                Text(
                    text = context.getString(R.string.no_internet_connection),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }

    }
}
