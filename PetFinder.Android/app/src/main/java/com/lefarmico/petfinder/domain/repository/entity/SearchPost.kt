package com.lefarmico.petfinder.domain.repository.entity

typealias PostId = String
typealias PostImageId = String
typealias ImageContent = Any

data class SearchPost(
    val postId: PostId,
    val header: String,
    val description: String,
    val image: PostImage
)

data class PostImage(
    val postImageId: PostImageId,
    val content: ImageContent,
    val size: ImageSizes
)

enum class ImageSizes {
    Xs, Sm, Md, Lg, Unrecognized
}
