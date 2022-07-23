package com.lefarmico.petfinder.presentation.screen.home.model

data class HomeState(
    val searchPostViewDataList: List<SearchPostViewData> = emptyList(),
    val userName: String = "",
    val isLoading: Boolean = false,
    val errorMsg: String? = null, // TODO : Change to ErrorMassage
)
