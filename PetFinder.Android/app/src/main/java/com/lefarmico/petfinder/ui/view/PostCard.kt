package com.lefarmico.petfinder.ui.view

import android.media.Image
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lefarmico.petfinder.R
import java.time.LocalDateTime

data class CardData(
    val author: String,
    val header: String,
    val photo: String?,
    val description: String,
    val releaseDate: LocalDateTime
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCard(
    modifier: Modifier = Modifier,
    cardData: CardData
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors()
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},

                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.Person,
                        contentDescription = "User"
                    )
                }
            }
            // Тестовое изображение
            Text(
                text = "Hello There",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                maxLines = 2
            )
//            GlideImage(
//                modifier = Modifier.fillMaxWidth(),
//                imageModel = cardData.photo,
//                contentScale = ContentScale.Crop,
//                placeHolder = painterResource(id = R.drawable.ic_launcher_foreground)
//            )
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Dog"
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
fun PastCard_Demo() {
    val data = CardData(
        author = "Artsiom Zharnikovich",
        header = "How to love dogs",
        photo = "https://www.mk.ru/social/2022/02/20/nazvany-5-samykh-zdorovykh-porod-sobak.html",
        description = "Few useless advises which are never help you to love someone.",
        releaseDate = LocalDateTime.now()
    )
    PostCard(cardData = data)
}
