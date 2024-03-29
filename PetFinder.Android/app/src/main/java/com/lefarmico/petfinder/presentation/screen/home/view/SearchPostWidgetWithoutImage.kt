package com.lefarmico.petfinder.presentation.screen.home.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lefarmico.petfinder.presentation.screen.home.model.SearchPostViewData
import com.lefarmico.petfinder.ui.theme.petfinder.PF_Card
import com.lefarmico.petfinder.ui.theme.petfinder.PF_OutlinedCard
import com.lefarmico.petfinder.ui.theme.petfinder.widget.RatingWidget
import com.lefarmico.petfinder.ui.theme.petfinder.widget.UserWidget

@Composable
fun SearchPostWidgetWithoutImage(
    modifier: Modifier = Modifier,
    searchPostViewData: SearchPostViewData,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onCardClick: () -> Unit = {},
    onUserClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onIncreaseRatingClick: () -> Unit = {},
    onDecreaseRatingClick: () -> Unit = {}
) {
    PF_Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable(
                onClick = onCardClick,
                enabled = enabled,
                interactionSource = interactionSource,
                role = Role.Button,
                indication = rememberRipple(
                    bounded = true
                )
            )
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(vertical = 8.dp),
        ) {
            val (
                userIcon, settingsButton, headerText,
                descriptionText, dateText, ratingView
            ) = createRefs()
            UserWidget(
                modifier = Modifier
                    .constrainAs(userIcon) {
                        start.linkTo(parent.start)
                        top.linkTo(settingsButton.top)
                        bottom.linkTo(settingsButton.bottom)
                    },
                userName = searchPostViewData.author,
                userImageUrl = searchPostViewData.authorImageContent as String,
                onClick = onUserClick
            )

            IconButton(
                modifier = Modifier
                    .constrainAs(settingsButton) {
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .size(40.dp),
                onClick = onSettingsClick
            ) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Menu")
            }
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(horizontal = 8.dp)
                    .constrainAs(headerText) {
                        top.linkTo(settingsButton.bottom)
                        start.linkTo(descriptionText.start)
                    },
                text = searchPostViewData.header,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2
            )
            Text(
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        top.linkTo(headerText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                text = searchPostViewData.description,
                maxLines = 2,
                style = MaterialTheme.typography.bodyMedium
            )
            PF_OutlinedCard(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .constrainAs(dateText) {
                        top.linkTo(ratingView.top)
                        bottom.linkTo(ratingView.bottom)
                        start.linkTo(descriptionText.start)
                    },
            ) {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .padding(horizontal = 4.dp),
                    text = searchPostViewData.publishedInfo,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            RatingWidget(
                modifier = Modifier
                    .constrainAs(ratingView) {
                        top.linkTo(descriptionText.bottom)
                        end.linkTo(descriptionText.end)
                        bottom.linkTo(parent.bottom)
                    },
                rating = searchPostViewData.rating,
                onDecreaseClick = onDecreaseRatingClick,
                onIncreaseClick = onIncreaseRatingClick
            )
        }
    }
}

@Preview(
    showBackground = true,
    group = "PostWidgetWithoutImage"
)
@Composable
fun PostWidgetWithoutImage_Demo() {
    val data = SearchPostViewData(
        author = "Artsiom Zharnikovich",
        authorImageContent = "https://www.india.com/wp-content/uploads/2017/11/12-3.jpg",
        header = "How to love dogs",
        description = " ",
        publishedInfo = "1 hour ago",
        postId = "1",
        imageContent = null,
        rating = 5,
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchPostWidgetWithoutImage(searchPostViewData = data)
    }
}
