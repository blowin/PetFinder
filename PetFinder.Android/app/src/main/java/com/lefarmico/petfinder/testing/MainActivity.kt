package com.lefarmico.petfinder.testing

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.lefarmico.petfinder.FlowEventsDemo
import com.lefarmico.petfinder.R
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowEventsDemo()
        }
    }
}
@Composable
fun CustomLayoutDemo() {
    SimpleFlexBox {
        for (i in 0..100) {
            ColoredBox()
        }
    }
}

@Composable
fun SimpleFlexBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content,
        measurePolicy = simpleFlexBoxMeasurePolicy()
    )
}

private fun simpleFlexBoxMeasurePolicy(): MeasurePolicy =
    MeasurePolicy { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        layout(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            var yPos = 0
            var xPos = 0
            var maxY = 0
            placeables.forEach { placeable ->
                if (xPos + placeable.width > constraints.maxWidth) {
                    xPos = 0
                    yPos += maxY
                    maxY = 0
                }
                placeable.placeRelative(
                    x = xPos,
                    y = yPos
                )
                xPos += placeable.width
                if (maxY < placeable.height) {
                    maxY = placeable.height
                }
            }
        }
    }

@Composable
fun ColoredBox() {
    Box(
        modifier = Modifier
            .border(width = 2.dp, color = Color.Black)
            .background(randomColor())
            .width((40 * randomInt123()).dp)
            .height((10 * randomInt123()).dp)
    )
}
private fun randomInt123() = Random.nextInt(1, 4)
fun randomColor() = when (randomInt123()) {
    1 -> Color.Red
    2 -> Color.Blue
    else -> Color.Green
}

@Preview(group = "ColText")
@Composable
fun ColumnWithText() {
    Column {
        Text(
            text = "Android UI development with Jetpack Compose",
            style = MaterialTheme.typography.h3
        )
        Text(
            text = "Hello Compose",
            style = MaterialTheme
                .typography
                .h5.merge(TextStyle(color = Color.Red))
        )
    }
}

@Composable
fun PredefinedLayoutDemo() {
    val red = remember { mutableStateOf(true) }
    val green = remember { mutableStateOf(true) }
    val blue = remember { mutableStateOf(true) }
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val (cbRed, cbGreen, cbBlue, boxRed, boxGreen, boxBlue) = createRefs()
        CheckBoxWithLabel(
            label = "red",
            state = red,
            modifier = Modifier.constrainAs(cbRed) {
                top.linkTo(parent.top)
            }
        )
        CheckBoxWithLabel(
            label = "green",
            state = green,
            modifier = Modifier.constrainAs(cbGreen) {
                top.linkTo(cbRed.bottom)
            }
        )
        CheckBoxWithLabel(
            label = "blue",
            state = blue,
            modifier = Modifier.constrainAs(cbBlue) {
                top.linkTo(cbGreen.bottom)
            }
        )
        if (red.value) {
            Box(
                modifier = Modifier
                    .background(Color.Red)
                    .constrainAs(boxRed) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(cbBlue.bottom, margin = 16.dp)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
        if (green.value) {
            Box(
                modifier = Modifier
                    .background(Color.Green)
                    .constrainAs(boxGreen) {
                        start.linkTo(parent.start, margin = 32.dp)
                        end.linkTo(parent.end, margin = 32.dp)
                        top.linkTo(cbBlue.bottom, margin = (16 + 32).dp)
                        bottom.linkTo(parent.bottom, margin = 32.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
        if (blue.value) {
            Box(
                modifier = Modifier
                    .background(Color.Blue)
                    .constrainAs(boxBlue) {
                        start.linkTo(parent.start, margin = 64.dp)
                        end.linkTo(parent.end, margin = 64.dp)
                        top.linkTo(cbBlue.bottom, margin = (16 + 64).dp)
                        bottom.linkTo(parent.bottom, margin = 64.dp)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
            )
        }
    }
}

@Composable
fun CheckBoxWithLabel(label: String, state: MutableState<Boolean>, modifier: Modifier = Modifier) {
    ConstraintLayout(
        modifier = modifier.clickable { state.value = !state.value }
    ) {
        val (checkbox, text) = createRefs()
        Checkbox(
            checked = state.value,
            onCheckedChange = { state.value = it },
            modifier = Modifier.constrainAs(checkbox) {}
        )
        Text(
            text = label,
            modifier = Modifier.constrainAs(text) {
                start.linkTo(checkbox.end, margin = 8.dp)
                top.linkTo(checkbox.top)
                bottom.linkTo(checkbox.bottom)
            }
        )
    }
}
@Composable
fun TextWithYellowBackground(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier.background(Color.Yellow),
        text = text
    )
}

fun Modifier.drawYellowCross() = then(
    object : DrawModifier {
        override fun ContentDrawScope.draw() {
            drawLine(
                color = Color.Yellow,
                start = Offset.Zero,
                end = Offset(size.width - 1, size.height - 1),
                strokeWidth = 10f
            )
            drawLine(
                color = Color.Yellow,
                start = Offset(0f, size.height - 1),
                end = Offset(size.width - 1, 0f),
                strokeWidth = 10f
            )
            drawContent()
        }
    }
)

@Composable
fun OrderDemo() {
    var color by remember { mutableStateOf(Color.Blue) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
            .border(BorderStroke(width = 2.dp, color = color))
            .background(Color.LightGray)
            .drawYellowCross()
            .clickable {
                color = if (color == Color.Blue) Color.Red else Color.Blue
            }
    )
}

@Composable
fun ColorPickMenu() {
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.width(min(400.dp, maxWidth)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val color = remember { mutableStateOf(Color.Magenta) }
            ColorPicker(color = color)
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color.value),
                text = "#${color.value.toArgb().toUInt().toString(16)}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.merge(
                    TextStyle(
                        color = color.value.complimentary()
                    )
                )
            )
        }
    }
}

@Composable
fun ColorPicker(color: MutableState<Color>) {
    val red = color.value.red
    val green = color.value.green
    val blue = color.value.blue
    val alpha = color.value.alpha

    Column {
        Slider(
            value = red,
            onValueChange = { color.value = Color(it, green, blue, alpha) }
        )
        Slider(
            value = green,
            onValueChange = { color.value = Color(red, it, blue, alpha) }
        )
        Slider(
            value = blue,
            onValueChange = { color.value = Color(red, green, it, alpha) }
        )
        Slider(
            value = alpha,
            onValueChange = { color.value = Color(red, green, blue, it) }
        )
    }
}

fun Color.complimentary() = Color(
    red = 1f - red,
    green = 1f - green,
    blue = 1f - blue
)

@Preview(group = "pr-1")
@Composable
fun Welcome() {
    Text(
        text = "Welcome",
        style = MaterialTheme.typography.subtitle1
    )
}

@Preview(
    group = "pr-2",
    showBackground = true,
    backgroundColor = 0xffff0000,
    showSystemUi = true,
    device = Devices.PIXEL_3A
)
@Composable
fun Greeting(@PreviewParameter(HelloProvider::class) name: String) {
    Text(
        text = stringResource(R.string.hello, name),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun TextAndButton(name: MutableState<String>, nameEntered: MutableState<Boolean>) {
    Row(modifier = Modifier.padding(top = 8.dp)) {
        TextField(
            value = name.value,
            onValueChange = {
                name.value = it
            },
            placeholder = {
                Text(text = stringResource(id = R.string.hint))
            },
            modifier = Modifier
                .alignByBaseline()
                .weight(1.0f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                capitalization = KeyboardCapitalization.Words
            ),
            keyboardActions = KeyboardActions(onAny = {
                nameEntered.value = true
            })
        )
        Button(
            modifier = Modifier
                .alignByBaseline()
                .padding(8.dp),
            onClick = {
                nameEntered.value = true
            }
        ) {
            Text(text = stringResource(id = R.string.done))
        }
    }
}

@Composable
fun Hello() {
    val name = remember { mutableStateOf("") }
    val nameEntered = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        if (nameEntered.value) {
            Greeting(name = name.value)
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Welcome()
                TextAndButton(name = name, nameEntered = nameEntered)
            }
        }
    }
}

class HelloProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = listOf("PreviewParameterProvider").asSequence()
}

fun factorialAsString(number: Int): String {
    var result = 1L
    for (i in 1..number) {
        result *= i
    }
    return "$number! = $result"
}

@Composable
fun Factorial() {
    var expander by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(factorialAsString(0)) }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                text = text,
                style = MaterialTheme.typography.h2
            )
            Button(
                onClick = {
                    expander = !expander
                }
            ) {
                Text(text = "Choose the number")
            }
        }
        DropdownMenu(
            expanded = expander,
            modifier = Modifier.background(Color.Blue),
            onDismissRequest = {
                expander = false
            }
        ) {
            for (i in 1 until 10) {
                DropdownMenuItem(
                    onClick = {
                        expander = false
                        text = factorialAsString(i)
                    }
                ) {
                    Text(
                        text = "$i!",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun ShortColoredText(text: String, color: Color) = Text(
    text = text,
    style = TextStyle(color)
)
