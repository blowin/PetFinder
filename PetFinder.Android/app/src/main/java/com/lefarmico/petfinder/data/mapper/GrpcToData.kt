package com.lefarmico.petfinder.data.mapper

import com.lefarmico.petfinder.domain.repository.entity.ImageData
import com.lefarmico.petfinder.domain.repository.entity.ImageSizes
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import com.lefarmico.proto.PetServiceProto
import java.time.LocalDateTime

fun PetServiceProto.SearchPostDetail.toData(): SearchPostData {
    val postImage = photosList[0]
    return SearchPostData(
        postId = id.value,
        header = title,
        description = description.data,
        image = ImageData(
            postImageId = postImage.id.value,
            content = postImage.content,
            size = postImage.size.toData()
        ),
        author = "",
        authorImage = ImageData(
            postImageId = "",
            content = "",
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
