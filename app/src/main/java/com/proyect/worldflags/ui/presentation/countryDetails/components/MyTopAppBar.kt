package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    onclick: () -> Unit,
    countryName: String,
    modifier: Modifier = Modifier
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onclick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back to home buttom",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        title = {
            Text(
                text = countryName,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = modifier
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor =  MaterialTheme.colorScheme.background
        )
    )
}