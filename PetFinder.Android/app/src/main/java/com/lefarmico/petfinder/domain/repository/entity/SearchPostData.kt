package com.lefarmico.petfinder.domain.repository.entity

import java.time.LocalDateTime

typealias PostId = String
typealias PostImageId = String
typealias ImageContent = Any

data class SearchPostData(
    val postId: PostId,
    val author: String,
    val authorImage: ImageData,
    val header: String,
    val description: String,
    val image: ImageData?,
    val rating: Int,
    val publishingDateTime: LocalDateTime
)

data class ImageData(
    val postImageId: PostImageId,
    val content: ImageContent,
    val size: ImageSizes
)

enum class ImageSizes {
    Xs, Sm, Md, Lg, Unrecognized
}
