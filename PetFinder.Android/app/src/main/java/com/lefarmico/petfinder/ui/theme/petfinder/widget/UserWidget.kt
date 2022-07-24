package com.lefarmico.petfinder.ui.theme.petfinder.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lefarmico.petfinder.R
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun UserWidget(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    userName: String,
    userImageUrl: String? = null,
) {
    Row(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                onClick = onClick,
                enabled = enabled,
                interactionSource = interactionSource,
                role = Role.Button,
                indication = rememberRipple(
                    bounded = true
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GlideImage(
            modifier = Modifier
                .padding(start = 8.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .width(24.dp)
                .height(24.dp),
            imageModel = userImageUrl,
            previewPlaceholder = R.drawable.beagle,
            failure = {
                Icon(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .width(24.dp)
                        .height(24.dp)
                        .background(MaterialTheme.colorScheme.surface),
                    imageVector = Icons.Filled.Person,
                    contentDescription = "User"
                )
            }
        )
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = userName,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Preview(
    showBackground = true,
    group = "ImageWithText"
)
@Composable
fun ImageWithTextClickableDemo() {
    UserWidget(userName = "Artsiom Zharnikovich")
}
