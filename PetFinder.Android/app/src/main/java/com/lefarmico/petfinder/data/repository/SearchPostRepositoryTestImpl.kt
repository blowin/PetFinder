package com.lefarmico.petfinder.data.repository

import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import com.lefarmico.petfinder.testData.TestDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchPostRepositoryTestImpl @Inject constructor(
    private val testData: TestDataSource
) : SearchPostRepository {

    override suspend fun getPost(postId: PostId): Flow<SearchPostData> = flow {
        val searchPost = testData.run { getSearchPost() }
        this.emit(searchPost)
    }

    override suspend fun getSearchPostRequest(page: Int, pageSize: Int): Flow<List<SearchPostData>> = flow {
        val searchPostList = testData.run { getSearchPosts() }
        this.emit(searchPostList)
    }
}
