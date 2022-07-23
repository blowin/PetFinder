package com.lefarmico.petfinder.domain.repository

import com.lefarmico.petfinder.domain.repository.entity.PostId
import com.lefarmico.petfinder.domain.repository.entity.SearchPost

interface SearchPostRepository {

    suspend fun getPost(postId: PostId): SearchPost

    suspend fun getSearchPostRequest(page: Int, pageSize: Int): List<SearchPost>
}
