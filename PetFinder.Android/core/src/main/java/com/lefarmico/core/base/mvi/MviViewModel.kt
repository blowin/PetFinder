package com.lefarmico.core.base.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lefarmico.core.base.entity.DataState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class MviViewModel<STATE : BaseState<*>, EVENT> : ViewModel() {

    private val _state = MutableStateFlow<BaseState<*>>(BaseState.Empty)
    val uiState = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, exception ->
        Timber.tag(EXCEPTION_HANDLER_TAG).e(exception)
        handleError(exception)
    }

    abstract fun onTriggerEvent(event: EVENT)

    protected fun setState(state: STATE) = safeLaunch {
        _state.emit(state)
    }

    open fun handleError(exception: Throwable) {
        _state.value = BaseState.Error(exception)
    }

    open fun startLoading() {
        _state.value = BaseState.Loading
    }

    /**
     * Lunches a new coroutine in viewModelScope with CoroutineExceptionHandler and blocked by CoroutineScope.
     */
    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    /**
     * For local requests which return object type
     */
    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completeHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { exception -> handleError(exception) }
            .collect { collector -> completeHandler(collector) }
    }

    /**
     * For remote requests which return DataState object type.
     */
    protected suspend fun <T> execute(
        callFlow: Flow<DataState<T>>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .onStart { startLoading() }
            .catch { handleError(it) }
            .collect { state ->
                when (state) {
                    is DataState.Success -> completionHandler.invoke(state.data)
                    is DataState.Error -> handleError(state.error)
                }
            }
    }

    companion object {
        private const val EXCEPTION_HANDLER_TAG = "MviViewModel-ExceptionHandler"
    }
}
