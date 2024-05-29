package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.proyect.worldflags.domain.model.CountryPreview
import com.proyect.worldflags.ui.navigation.Routes

@Composable
fun BorderCountries(
    borderCountries:  List<CountryPreview>,
    navController: NavHostController
){
    Text(text = "Países en frontera:", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier, color = MaterialTheme.colorScheme.onBackground)

    LazyVerticalGrid(columns = GridCells.Fixed(5)){
        items(borderCountries){
            BorderCountryItem(
                name = it.commonName,
                flag = it.pngFlagUrl,
                onclick = {
                    navController.navigate(Routes.CountryDetailScreen.withArgs(it.id))
                },
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}