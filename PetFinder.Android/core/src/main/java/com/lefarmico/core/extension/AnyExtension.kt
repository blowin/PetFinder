package com.lefarmico.core.extension

val Any.classTag: String get() = this.javaClass.canonicalName.orEmpty()

val Any.methodTag: String get() = classTag + object : Any() {}.javaClass.enclosingMethod?.name

fun Any.hashCodeAsString(): String { return hashCode().toString() }

inline fun <reified T : Any> Any.cast(): T { return this as T }
