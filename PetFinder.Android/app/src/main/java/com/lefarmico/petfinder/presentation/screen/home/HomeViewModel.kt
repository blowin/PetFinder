package com.lefarmico.petfinder.presentation.screen.home

import androidx.lifecycle.viewModelScope
import com.lefarmico.core.base.mvi.MviViewModel
import com.lefarmico.petfinder.domain.repository.SearchPostRepository
import com.lefarmico.petfinder.presentation.screen.home.model.HomeEvent
import com.lefarmico.petfinder.presentation.screen.home.model.HomeState
import com.lefarmico.petfinder.presentation.screen.home.model.toViewData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchPostRepository: SearchPostRepository
) : MviViewModel<HomeState, HomeEvent>() {

    override fun onTriggerEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.GetSearchPosts -> getSearchPostList()
        }
    }

    override val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    override val state: StateFlow<HomeState> = _state.asStateFlow()

    fun getSearchPostList() {
        viewModelScope.launch {
            val searchPostList = withContext(Dispatchers.IO) {
                searchPostRepository.getSearchPostRequest(1, 1)
                    .onStart {
                        _state.value = _state.value.copy(isLoading = true)
                    }.catch { cause ->
                        // TODO: Send errMsg
                    }.firstOrNull()
            }
            _state.value = _state.value.copy(
                isLoading = true,
                searchPostViewDataList = searchPostList?.map { it.toViewData() } ?: emptyList()
            )
        }
    }
}
