package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun BorderCountryItem(
    flag: String,
    name: String,
    onclick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable { onclick() }
            .padding(vertical = 16.dp)
            .width(70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = flag,
            contentDescription = "Flag of $name",
            contentScale = ContentScale.FillHeight,
            filterQuality = FilterQuality.Medium,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(40.dp)
            ,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = name,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}