package com.pooyan.test.views

import android.annotation.SuppressLint
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.unit.dp
import com.pooyan.test.R

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun LikeIcon(
    isLiked: Boolean,
    likes: Int,
    likedState: MutableState<Boolean>,
    onLikeClick: (Int) -> Unit
) {
    Row {
        IconToggleButton(
            checked = likedState.value,
            onCheckedChange = {
                likedState.value = !likedState.value
            }
        ) {
            val transition = updateTransition(likedState.value, label = "iconTransition")

            val size by transition.animateDp(
                transitionSpec = {
                    if (false isTransitioningTo true) {
                        keyframes {
                            durationMillis = 250
                            // specifying animations
                            30.dp at 0 with LinearOutSlowInEasing // for 0-15 ms
                            35.dp at 15 with FastOutLinearInEasing // for 15-75 ms
                            40.dp at 75 // ms
                            35.dp at 150 // ms
                        }
                    } else {
                        spring(stiffness = Spring.StiffnessVeryLow)
                    }
                },
                label = "Size"
            ) { 30.dp }

            Icon(
                imageVector = if (likedState.value)
                    Icons.Filled.Favorite
                else Icons.Filled.FavoriteBorder,
                contentDescription = "Icon",
                tint = Color.Red,
                modifier = Modifier.size(size)
            )
        }

        val likeCount = when {
            !likedState.value && isLiked -> likes.minus(1)
            likedState.value && !isLiked -> likes.plus(1)
            else -> likes
        }
        onLikeClick(likeCount)
        Text(
            text = pluralStringResource(id = R.plurals.likes, count = likeCount, likeCount),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}