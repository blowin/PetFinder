package com.lefarmico.petfinder.testData

import com.lefarmico.petfinder.domain.repository.entity.ImageData
import com.lefarmico.petfinder.domain.repository.entity.ImageSizes
import com.lefarmico.petfinder.domain.repository.entity.SearchPostData
import java.time.LocalDateTime

class TestDataSource {

    private val searchPostDataTest = SearchPostData(
        postId = "1",
        header = "Why do we love dogs?",
        description = "We love dogs. Just about all of us do. Big dogs, small dogs, yappy dogs, fluffy dogs, all of them are loveable dogs — we call them pups and puppers, woofers and boofers, pupperinos and cutie-booties, and we adore our closest companions like no other.",
        image = ImageData(
            postImageId = "1",
            content = "https://miro.medium.com/max/700/1*WQwik9o-_p3R7i5SfMDPlQ.jpeg",
            size = ImageSizes.Lg
        ),
        author = "",
        authorImage = ImageData(
            postImageId = "2",
            content = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fsteamcdn-a.akamaihd.net%2Fsteamcommunity%2Fpublic%2Fimages%2Favatars%2F92%2F929b316f1adb43c47289032b62213c82f9c0b8e0_full.jpg&f=1&nofb=1",
            size = ImageSizes.Sm
        ),
        rating = 0,
        publishingDateTime = LocalDateTime.now()
    )

    private val searchPostDataTestNoImage = SearchPostData(
        postId = "1",
        header = "Why do we love dogs?",
        description = "We love dogs. Just about all of us do. Big dogs, small dogs, yappy dogs, fluffy dogs, all of them are loveable dogs — we call them pups and puppers, woofers and boofers, pupperinos and cutie-booties, and we adore our closest companions like no other.",
        image = null,
        author = "",
        authorImage = ImageData(
            postImageId = "2",
            content = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fsteamcdn-a.akamaihd.net%2Fsteamcommunity%2Fpublic%2Fimages%2Favatars%2F92%2F929b316f1adb43c47289032b62213c82f9c0b8e0_full.jpg&f=1&nofb=1",
            size = ImageSizes.Sm
        ),
        rating = 0,
        publishingDateTime = LocalDateTime.now()
    )

    fun getSearchPost() = searchPostDataTest

    fun getSearchPosts(): List<SearchPostData> {
        val searchPostDataList = mutableListOf<SearchPostData>()
        for (i in 1..10) {
            val item = when (i % 2 == 0) {
                true -> searchPostDataTest
                false -> searchPostDataTestNoImage
            }
            searchPostDataList.add(item)
        }
        return searchPostDataList
    }
}
