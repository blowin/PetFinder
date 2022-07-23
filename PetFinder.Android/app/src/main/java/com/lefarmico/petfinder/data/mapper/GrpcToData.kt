package com.lefarmico.petfinder.data.mapper

import com.lefarmico.petfinder.domain.repository.entity.ImageSizes
import com.lefarmico.petfinder.domain.repository.entity.PostImage
import com.lefarmico.petfinder.domain.repository.entity.SearchPost
import com.lefarmico.proto.PetServiceProto

fun PetServiceProto.SearchPostDetail.toData(): SearchPost {
    val postImage = photosList[0]
    return SearchPost(
        postId = id.value,
        header = title,
        description = description.data,
        image = PostImage(
            postImageId = postImage.id.value,
            content = postImage.content,
            size = postImage.size.toData()
        )
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
