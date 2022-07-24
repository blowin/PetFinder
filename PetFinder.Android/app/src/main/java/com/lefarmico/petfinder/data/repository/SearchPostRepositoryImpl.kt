package com.lefarmico.petfinder.data.repository

import com.lefarmico.petfinder.data.mapper.toData
import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import com.lefarmico.petfinder.testData.TestSearchPostDataSource
import com.lefarmico.proto.PetServiceGrpcGrpc
import com.lefarmico.proto.PetServiceProto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPostRepositoryImpl @Inject constructor(
    private val blockingStub: PetServiceGrpcGrpc.PetServiceGrpcBlockingStub
) : SearchPostRepository {
    override suspend fun getPost(postId: PostId): Flow<SearchPostData> = flow {
//        val searchPost = blockingStub.getSearchPost(
//            PetServiceProto.GetByIdRequest.getDefaultInstance()
//        )
        val searchPostw = TestSearchPostDataSource().run { getSearchPost() }
        this.emit(searchPostw)
    }

    override suspend fun getSearchPostRequest(page: Int, pageSize: Int): Flow<List<SearchPostData>> = flow {
        val searchPostDataList = mutableListOf<SearchPostData>()
        blockingStub.getSearchPosts(
            PetServiceProto.GetSearchPostsRequest
                .getDefaultInstance()
                .newBuilderForType()
                .setPageRequest(
                    PetServiceProto.PageRequest.newBuilder()
                        .setPage(1)
                        .setPageSize(10)
                        .build()
                ).setImageSize(
                    PetServiceProto.ImageSize.Md
                ).build()
        ).forEachRemaining {
            val searchPostData = it.toData()
            searchPostDataList.add(searchPostData)
        }
        this.emit(searchPostDataList)
    }
}
