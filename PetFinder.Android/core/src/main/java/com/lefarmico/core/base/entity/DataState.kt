package com.lefarmico.core.base.entity

import com.lefarmico.core.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.transform

sealed class DataState<out T> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val error: Throwable) : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success [data: $data]"
            is Error -> "Error [exception: $error]"
        }
    }

    inline fun <M : Any> map(transform: (T) -> R): DataState<R> {
        return when (this) {
            is Success -> Success(transform(this.data))
            is Error -> Error(this.error)
        }
    }

    suspend inline fun <R : Any> suspendMap(crossinline transform: suspend (T) -> R): DataState<R> {
        return when (this) {
            is Success -> Success(transform(this.data))
            is Error -> Error(this.error)
        }
    }

    fun <T : Any> Flow<T>.asDataStateFlow(): Flow<DataState<T>> = wrapDataState()
        .catch { exception -> emit(DataState.Error(exception)) }

    fun <T : Any> Flow<T>.wrapDataState(): Flow<DataState<T>> = transform { value ->
        emit(DataState.Success(value))
    }

    inline fun <T : Any, R : Any> Flow<DataState<T>>.mapDataState(
        crossinline transform: suspend (value: T) -> R
    ): Flow<DataState<R>> = transform { value -> emit(value.suspendMap(transform)) }

    inline fun <T : Any> Flow<DataState<T>>.onSuccess(
        crossinline action: suspend (value: T) -> Unit
    ): Flow<DataState<T>> = onEach { if (it is DataState.Success) action(it.data) }

    inline fun <T : Any> Flow<DataState<T>>.onError(
        crossinline action: suspend (error: Throwable) -> R
    ): Flow<DataState<T>> = onEach { if (it is DataState.Error) action(it.error) }
}
