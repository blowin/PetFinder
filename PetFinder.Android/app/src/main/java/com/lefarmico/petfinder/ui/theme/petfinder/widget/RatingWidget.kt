package com.lefarmico.petfinder.ui.theme.petfinder.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.lefarmico.petfinder.ui.view.FadedText
import com.lefarmico.petfinder.utils.imageVector.RemoveCircle

// TODO : Change it top more material
@Composable
fun RatingWidget(
    modifier: Modifier = Modifier,
    rating: Int,
    onIncreaseClick: () -> Unit = {},
    onDecreaseClick: () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable(
                    onClick = onIncreaseClick,
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    role = Role.Button,
                    indication = rememberRipple(
                        bounded = false
                    )
                ),
            imageVector = Icons.Filled.AddCircle,
            contentDescription = "Increase rating"
        )
        FadedText(
            modifier = Modifier.padding(8.dp),
            text = rating.toString(),
            style = MaterialTheme.typography.labelLarge
        )
        Icon(
            modifier = Modifier
                .padding(8.dp)
                .clickable(
                    onClick = onDecreaseClick,
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    role = Role.Button,
                    indication = rememberRipple(
                        bounded = false
                    )
                ),
            imageVector = Icons.Filled.RemoveCircle,
            contentDescription = "Decrease rating"
        )
    }
}
