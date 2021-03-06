package com.lefarmico.petfinder.utils.imageVector

import androidx.compose.material.icons.materialIcon
import androidx.compose.material.icons.materialPath
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview

val RemoveCircle: ImageVector
    get() {
        if (_removeCircle != null) {
            return _removeCircle!!
        }
        _removeCircle = materialIcon(name = "Filled.RemoveCircle") {
            materialPath {
                moveTo(12.0f, 2.0f)
                curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f)
                reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f)
                reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f)
                reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f)
                close()
                moveTo(17.0f, 13.0f)
                horizontalLineToRelative(-10.0f)
                verticalLineToRelative(-2.0f)
                horizontalLineToRelative(10.0f)
                verticalLineToRelative(2.0f)
                close()
            }
        }
        return _removeCircle!!
    }

private var _removeCircle: ImageVector? = null

@Preview
@Composable
fun IconRemoveCircleDemo() {
    Icon(imageVector = RemoveCircle, contentDescription = "")
}
