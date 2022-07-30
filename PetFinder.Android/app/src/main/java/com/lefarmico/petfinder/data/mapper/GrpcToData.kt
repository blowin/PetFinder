package com.lefarmico.petfinder.data.mapper

import com.lefarmico.petfinder.domain.repository.entity.ImageData
import com.lefarmico.petfinder.domain.repository.entity.ImageSizes
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import com.lefarmico.proto.PetServiceProto
import java.time.LocalDateTime

fun PetServiceProto.SearchPostDetail.toData(): SearchPostData {
    val postImage = photosList.map {
        ImageData(
            postImageId = it.id.value,
            content = it.content,
            size = it.size.toData()
        )
    }.firstOrNull()
    return SearchPostData(
        postId = id.value,
        header = title,
        description = description.data,
        image = postImage,
        author = "",
        authorImage = ImageData(
            postImageId = "",
            content = "https://www.india.com/wp-content/uploads/2017/11/12-3.jpg",
            size = ImageSizes.Unrecognized
        ),
        rating = 0,
        publishingDateTime = LocalDateTime.now()
    )
}

fun PetServiceProto.ImageSize.toData(): ImageSizes {
    return when (this) {
        PetServiceProto.ImageSize.Xs -> ImageSizes.Xs
        PetServiceProto.ImageSize.Sm -> ImageSizes.Sm
        PetServiceProto.ImageSize.Md -> ImageSizes.Md
        PetServiceProto.ImageSize.Lg -> ImageSizes.Lg
        PetServiceProto.ImageSize.UNRECOGNIZED -> ImageSizes.Unrecognized
        else -> throw (
            NotImplementedError(
                "${this.name} size is not Implemented"
            )
            )
    }
}
