package com.proyect.worldflags.ui.presentation.countryDetails.components

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MyTopAppBar(
    onclick: () -> Unit,
    countryName: String,
    modifier: Modifier = Modifier,
    animatedVisibilityScope: AnimatedVisibilityScope
){
    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onclick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    .sharedElement(
                        state = rememberSharedContentState(key = "countryName:/$countryName"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _,_->
                            tween(1000)
                        }
                    )
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor =  MaterialTheme.colorScheme.background
        )
    )
}