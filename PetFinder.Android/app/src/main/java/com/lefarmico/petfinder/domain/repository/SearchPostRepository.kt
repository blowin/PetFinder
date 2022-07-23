package com.lefarmico.petfinder.domain.repository

import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import kotlinx.coroutines.flow.Flow

interface SearchPostRepository {

    suspend fun getPost(postId: PostId): Flow<SearchPostData>

    suspend fun getSearchPostRequest(page: Int, pageSize: Int): Flow<List<SearchPostData>>
}
