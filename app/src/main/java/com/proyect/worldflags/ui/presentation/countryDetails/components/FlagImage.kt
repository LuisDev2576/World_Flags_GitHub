package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FlagImage(
    contentDescription: String,
    image: Any?,
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
){
    AsyncImage(
        model = image,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        filterQuality = FilterQuality.High,
        modifier = modifier
            .sharedElement(
                state = rememberSharedContentState(key = "countryFlag:/$image"),
                animatedVisibilityScope = animatedVisibilityScope,
                boundsTransform = { _,_->
                    tween(1000)
                }
            )
            .fillMaxWidth()
            .heightIn(max = 250.dp)
            .border(1.dp, MaterialTheme.colorScheme.surface, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp)),
        alignment = Alignment.Center,
    )
}