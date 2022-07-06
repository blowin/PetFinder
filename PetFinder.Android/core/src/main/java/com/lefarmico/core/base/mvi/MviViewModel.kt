package com.lefarmico.core.base.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class MviViewModel<STATE, EVENT> : ViewModel() {

    /**
     * Initial data state.
     */
    protected abstract val _state: MutableStateFlow<STATE>
    abstract val state: StateFlow<STATE>

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(EXCEPTION_HANDLER_TAG).e(exception)
    }

    abstract fun onTriggerEvent(event: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        this@MviViewModel._state.emit(state)
    }

    /**
     * Lunches a new coroutine in viewModelScope with CoroutineExceptionHandler and blocked by CoroutineScope.
     */
    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    companion object {
        private const val EXCEPTION_HANDLER_TAG = "MviViewModel-ExceptionHandler"
    }
}
