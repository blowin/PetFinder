package com.lefarmico.petfinder.data.repository

import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPost

class SearchPostRepositoryImpl : SearchPostRepository {
    override suspend fun getPost(postId: PostId): SearchPost {
//        val channel = ManagedChannelBuilder.forAddress("https://localhost/", 8300).build()
//        val blockingStub = PetServiceGrpcGrpc.newBlockingStub(channel)
//        val postRequest = PetServiceProto.GetByIdRequest.newBuilder()
//            .setId(PetServiceProto.UUID.getDefaultInstance())
//            .build()
//        var post = blockingStub.getSearchPost(postRequest)
        TODO("Not yet implemented")
    }

    override suspend fun getSearchPostRequest(page: Int, pageSize: Int): List<SearchPost> {
        TODO("Not yet implemented")
    }
}
