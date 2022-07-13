package com.lefarmico.petfinder.ui.view.validator

class TextValidationHandler(
    vararg validators: TextValidator
) : ValidatorController {

    private val validatorList = mutableListOf<TextValidator>()
        .apply {
            addAll(validators)
        }

    override fun addValidator(validator: TextValidator) {
        validatorList.add(validator)
    }

    internal fun validate(text: String): ValidationResponse? {
        validatorList.forEach {
            val response = it.validate(text)
            if (response != null) {
                return response
            }
        }
        return null
    }
}

interface ValidatorController {
    fun addValidator(validator: TextValidator)
}

interface TextValidator {
    fun validate(text: String): ValidationResponse?

    companion object {
        val EmptyText = emptyTextValidator()
        val DefPassword = defaultPasswordTextValidator(5)
        val Email = emailTextValidator()
    }
}

// TODO: переместить текст ошибки в ресурсы
fun emptyTextValidator(): TextValidator {
    return object : TextValidator {
        override fun validate(text: String): ValidationResponse? {
            return if (text.isEmpty()) {
                ValidationResponse("The text should not be empty")
            } else {
                null
            }
        }
    }
}

fun defaultPasswordTextValidator(minPasswordLength: Int): TextValidator {
    return object : TextValidator {
        override fun validate(text: String): ValidationResponse? {
            return if (text.length < minPasswordLength) {
                ValidationResponse("Min length is $minPasswordLength symbols")
            } else {
                null
            }
        }
    }
}

fun emailTextValidator(): TextValidator {
    return object : TextValidator {
        override fun validate(text: String): ValidationResponse? {
            return if (!text.contains("@")) {
                ValidationResponse("Incorrect email")
            } else {
                null
            }
        }
    }
}

data class ValidationResponse(val response: String)
