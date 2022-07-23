package com.lefarmico.petfinder.data.repository

import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import com.lefarmico.petfinder.testData.TestSearchPostDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPostRepositoryImpl @Inject constructor() : SearchPostRepository {
    override suspend fun getPost(postId: PostId): Flow<SearchPostData> = flow {
        val searchPost = TestSearchPostDataSource().run { getSearchPost() }
        this.emit(searchPost)
    }

    override suspend fun getSearchPostRequest(page: Int, pageSize: Int): Flow<List<SearchPostData>> = flow {
        val searchPostList = TestSearchPostDataSource().run { getSearchPosts() }
        this.emit(searchPostList)
    }
}
//        val channel = ManagedChannelBuilder.forAddress("https://localhost/", 8300).build()
//        val blockingStub = PetServiceGrpcGrpc.newBlockingStub(channel)
//        val postRequest = PetServiceProto.GetByIdRequest.newBuilder()
//            .setId(PetServiceProto.UUID.getDefaultInstance())
//            .build()
//        var post = blockingStub.getSearchPost(postRequest)
