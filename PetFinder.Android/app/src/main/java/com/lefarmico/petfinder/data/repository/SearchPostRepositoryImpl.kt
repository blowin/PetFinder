package com.lefarmico.petfinder.data.repository

import android.util.Log
import com.lefarmico.petfinder.data.mapper.toData
import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import com.lefarmico.proto.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SearchPostRepositoryImpl @Inject constructor(
    private val blockingStub: PetServiceGrpcGrpc.PetServiceGrpcBlockingStub
) : SearchPostRepository {
    override suspend fun getPost(postId: PostId): Flow<SearchPostData> = flow {
        val getByIdRequest = getByIdRequest {
            id = uUID { value = postId }
        }
        val searchPost = blockingStub.getSearchPost(getByIdRequest)
            .also { Log.d(RESPONSE_TAG, "postResponse: [uuid: ${it.id.value}") }
            .toData()
        emit(searchPost)
    }

    override suspend fun getSearchPostRequest(page: Int, pageSize: Int): Flow<List<SearchPostData>> {
        val pageRequest = pageRequest {
            this.page = page
            this.pageSize = pageSize
        }.also {
            Log.d(
                REQUEST_TAG,
                "[pageRequest]: " +
                    "page = ${it.page} " +
                    "pageSize = ${it.pageSize} "
            )
        }
        val getSearchPostsRequest = getSearchPostsRequest {
            this.pageRequest = pageRequest
        }.also {
            Log.d(
                REQUEST_TAG,
                "[postsRequest]: " +
                    "imageSize: ${it.imageSize} " +
                    "imageSizeValue: ${it.imageSizeValue} "
            )
        }
        return flow {
            val searchPostList = blockingStub.getSearchPosts(getSearchPostsRequest)
                .asSequence()
                .toList()
                .map { it.toData() }
                .also { Log.d(RESPONSE_TAG, "[postsResponse]: $it, size: ${it.size}") }

            emit(searchPostList)
        }
            .flowOn(Dispatchers.IO)
            .catch {
                Log.e(RESPONSE_TAG, "Response error", it)
                error(it.printStackTrace())
            }
    }

    companion object {
        const val REQUEST_TAG = "REQUEST_GRPC_SEARCH_POST"
        const val RESPONSE_TAG = "RESPONSE_GRPC_SEARCH_POST"
    }
}
