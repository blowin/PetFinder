package com.lefarmico.core.base.mvi

sealed interface BaseState<out T> {
    object Loading : BaseState<Nothing>
    object Empty : BaseState<Nothing>
    data class Data<T>(val value: T) : BaseState<T>
    data class Error(val throwable: Throwable) : BaseState<Nothing>
}
