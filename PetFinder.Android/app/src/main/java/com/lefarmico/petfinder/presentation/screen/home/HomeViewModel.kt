package com.lefarmico.petfinder.presentation.screen.home

import com.lefarmico.core.base.mvi.BaseState
import com.lefarmico.core.base.mvi.MviViewModel
import com.lefarmico.petfinder.presentation.screen.home.model.HomeEvent
import com.lefarmico.petfinder.presentation.screen.home.model.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : MviViewModel<BaseState<HomeState>, HomeEvent>() {

    override fun onTriggerEvent(event: HomeEvent) {
        TODO("Not yet implemented")
    }

    override val _state: MutableStateFlow<BaseState<HomeState>>
        get() = TODO("Not yet implemented")
    override val state: StateFlow<BaseState<HomeState>>
        get() = TODO("Not yet implemented")
}
