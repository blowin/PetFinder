package com.lefarmico.petfinder.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class MyViewModel : ViewModel() {
    private val _text: MutableLiveData<String> = MutableLiveData("Hello #3")

    val text: LiveData<String>
        get() = _text

    fun setText(value: String) {
        _text.value = value
    }
}

class ViewModelDemoActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewModelDemo()
        }
    }
}

@Composable
private fun ViewModelDemo() {
    val viewModel: MyViewModel = viewModel()
    val state1 = remember { mutableStateOf("Hello #1") }
    val state2 = remember { mutableStateOf("Hello #2") }
    val state3 = viewModel.text.observeAsState()

    state3.value?.let {
        Column(modifier = Modifier.fillMaxWidth()) {
            MyTextField(state1) { state1.value = it }
            MyTextField(state2) { state2.value = it }
            MyTextField(state3) { viewModel.setText(it) }
        }
    }
}
@Composable
fun MyTextField(
    value: State<String?>,
    onValueChanged: (String) -> Unit
) {
    value.value?.let {
        TextField(
            value = it,
            onValueChange = onValueChanged,
            modifier = Modifier.fillMaxSize()
        )
    }
}
