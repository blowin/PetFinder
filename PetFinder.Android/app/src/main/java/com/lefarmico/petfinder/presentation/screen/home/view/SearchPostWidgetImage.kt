package com.lefarmico.petfinder.presentation.screen.home.view

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.lefarmico.petfinder.R
import com.lefarmico.petfinder.presentation.screen.home.model.SearchPostViewData
import com.lefarmico.petfinder.ui.theme.petfinder.PF_Card
import com.lefarmico.petfinder.ui.theme.petfinder.PF_OutlinedCard
import com.lefarmico.petfinder.ui.theme.petfinder.widget.RatingWidget
import com.lefarmico.petfinder.ui.theme.petfinder.widget.UserWidget
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SearchPostWidget(
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
    when (searchPostViewData.imageContent) {
        null -> SearchPostWidgetWithoutImage(
            modifier = modifier,
            searchPostViewData = searchPostViewData,
            enabled = enabled,
            interactionSource = interactionSource,
            onCardClick = onCardClick,
            onUserClick = onUserClick,
            onSettingsClick = onSettingsClick,
            onIncreaseRatingClick = onIncreaseRatingClick,
            onDecreaseRatingClick = onDecreaseRatingClick
        )
        else -> SearchPostWidgetImage(
            modifier = modifier,
            searchPostViewData = searchPostViewData,
            enabled = enabled,
            interactionSource = interactionSource,
            onCardClick = onCardClick,
            onUserClick = onUserClick,
            onSettingsClick = onSettingsClick,
            onIncreaseRatingClick = onIncreaseRatingClick,
            onDecreaseRatingClick = onDecreaseRatingClick
        )
    }
}

@Composable
fun SearchPostWidgetImage(
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
                postImage, descriptionText, dateText, ratingView
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
            GlideImage(
                modifier = Modifier
                    .constrainAs(postImage) {
                        top.linkTo(headerText.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(bottom = 8.dp)
                    .fillMaxWidth()
                    .height(200.dp),
                imageModel = searchPostViewData.imageContent,
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colorScheme.background,
                    highlightColor = Color.DarkGray,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                previewPlaceholder = R.drawable.beagle,
                contentScale = ContentScale.FillWidth,
            )
            Text(
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        top.linkTo(postImage.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
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

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    group = "PostCard"
)
@Composable
fun PostWidget_Demo() {
    val data = SearchPostViewData(
        author = "Artsiom Zharnikovich",
        authorImageContent = "https://www.india.com/wp-content/uploads/2017/11/12-3.jpg",
        header = "How to love dogs",
        imageContent = "https://images.hellogiggles.com/uploads/2015/11/03/dog.jpg",
        description = "Few useless advises which are never help you to love someone.",
        publishedInfo = "1 hour ago",
        postId = "",
        rating = 5,
    )
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchPostWidget(searchPostViewData = data)
        SearchPostWidget(searchPostViewData = data.copy(imageContent = null))
    }
}
