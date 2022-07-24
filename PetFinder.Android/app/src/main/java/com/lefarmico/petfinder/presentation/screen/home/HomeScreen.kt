package com.lefarmico.petfinder.presentation.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lefarmico.petfinder.presentation.navigation.NavigationActions
import com.lefarmico.petfinder.presentation.screen.home.model.HomeEvent
import com.lefarmico.petfinder.presentation.screen.home.view.SearchPostWidget

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    navigationActions: NavigationActions
) {
    viewModel.onTriggerEvent(HomeEvent.GetSearchPosts)
    val state = viewModel.state.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(state.value.searchPostViewDataList) { searchPost ->
            SearchPostWidget(
                modifier = modifier,
                searchPostViewData = searchPost
            )
        }
    }
}
