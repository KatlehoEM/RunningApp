package com.example.a2020102527_main_project_2.ui.utils
/*
    Surname and Initials: Makhooane KE
    Student Number: 2020102527
    Date: 16 May 2024
    Module Code: CSIP6853
*/
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
    The ComposeUtils object provides a composable function SlideDownAnimatedVisibility for
    animating visibility changes with sliding effects in Jetpack Compose.
    It utilizes the AnimatedVisibility composable along with slideInVertically and slideOutVertically
     animations, enabling smooth entrance and exit transitions. This utility simplifies the implementation
     of sliding animations for components based on visibility changes in Compose UIs.
 */
object ComposeUtils {
    const val slideDownInDuration = 250
    const val slideDownOutDuration = 250

    @Composable
    fun SlideDownAnimatedVisibility(
        modifier: Modifier = Modifier,
        inDurationInMillis: Int = slideDownInDuration,
        outDurationInMillis: Int = slideDownOutDuration,
        visible: Boolean,
        content: @Composable AnimatedVisibilityScope.() -> Unit
    ) {
        AnimatedVisibility(
            modifier = modifier,
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = inDurationInMillis,
                    easing = LinearOutSlowInEasing
                )
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = outDurationInMillis,
                    easing = FastOutLinearInEasing
                )
            ),
            content = content
        )
    }
}