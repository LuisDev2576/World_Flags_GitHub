package com.proyect.worldflags.ui.presentation.countriesHome.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import com.proyect.worldflags.R

@Composable
fun LogoImage(imageState: AsyncImagePainter.State) {

    val context = LocalContext.current

    imageState.painter?.let {
        Image(
            modifier = Modifier
                .padding(bottom = 16.dp)
                .height(25.dp),
            painter = it,
            contentDescription = context.getString(R.string.app_name_description),
            contentScale = ContentScale.FillHeight,
            alignment = Alignment.Center
        )
    }

}