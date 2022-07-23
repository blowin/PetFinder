package com.lefarmico.petfinder.presentation.screen.home.model

import com.lefarmico.petfinder.domain.repository.entity.ImageContent
import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class SearchPostViewData(
    val postId: PostId,
    val author: String,
    val authorImageContent: ImageContent,
    val header: String,
    val description: String,
    val imageContent: ImageContent?,
    val rating: Int,
    val publishedInfo: String
)

fun SearchPostData.toViewData() = SearchPostViewData(
    postId = postId,
    author = author,
    authorImageContent = authorImage.content,
    header = header,
    description = description,
    imageContent = image?.content,
    rating = rating,
    publishedInfo = publishingDateTime.publishedFormat()
)

fun LocalDateTime.publishedFormat(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return this.format(formatter)
}
