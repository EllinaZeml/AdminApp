package org.example.project
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object Icons {

    val Quiet_time: ImageVector
        get() {
            if (_Quiet_time != null) {
                return _Quiet_time!!
            }
            _Quiet_time = ImageVector.Builder(
                name = "Quiet_time",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(524f, 920f)
                    quadToRelative(-84f, 0f, -157.5f, -32f)
                    reflectiveQuadToRelative(-128f, -86.5f)
                    reflectiveQuadToRelative(-86.5f, -128f)
                    reflectiveQuadTo(120f, 516f)
                    quadToRelative(0f, -146f, 93f, -257.5f)
                    reflectiveQuadTo(450f, 120f)
                    quadToRelative(-18f, 99f, 11f, 193.5f)
                    reflectiveQuadTo(561f, 479f)
                    reflectiveQuadToRelative(165.5f, 100f)
                    reflectiveQuadTo(920f, 590f)
                    quadToRelative(-26f, 144f, -138f, 237f)
                    reflectiveQuadTo(524f, 920f)
                    moveToRelative(0f, -80f)
                    quadToRelative(88f, 0f, 163f, -44f)
                    reflectiveQuadToRelative(118f, -121f)
                    quadToRelative(-86f, -8f, -163f, -43.5f)
                    reflectiveQuadTo(504f, 535f)
                    reflectiveQuadToRelative(-97f, -138f)
                    reflectiveQuadToRelative(-43f, -163f)
                    quadToRelative(-77f, 43f, -120.5f, 118.5f)
                    reflectiveQuadTo(200f, 516f)
                    quadToRelative(0f, 135f, 94.5f, 229.5f)
                    reflectiveQuadTo(524f, 840f)
                    moveToRelative(-20f, -305f)
                }
            }.build()
            return _Quiet_time!!
        }

    private var _Quiet_time: ImageVector? = null

    val Notifications: ImageVector
        get() {
            if (_Notifications != null) {
                return _Notifications!!
            }
            _Notifications = ImageVector.Builder(
                name = "Notifications",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(160f, 760f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(-280f)
                    quadToRelative(0f, -83f, 50f, -147.5f)
                    reflectiveQuadTo(420f, 168f)
                    verticalLineToRelative(-28f)
                    quadToRelative(0f, -25f, 17.5f, -42.5f)
                    reflectiveQuadTo(480f, 80f)
                    reflectiveQuadToRelative(42.5f, 17.5f)
                    reflectiveQuadTo(540f, 140f)
                    verticalLineToRelative(28f)
                    quadToRelative(80f, 20f, 130f, 84.5f)
                    reflectiveQuadTo(720f, 400f)
                    verticalLineToRelative(280f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(80f)
                    close()
                    moveTo(480f, 880f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(400f, 800f)
                    horizontalLineToRelative(160f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(480f, 880f)
                    moveTo(320f, 680f)
                    horizontalLineToRelative(320f)
                    verticalLineToRelative(-280f)
                    quadToRelative(0f, -66f, -47f, -113f)
                    reflectiveQuadToRelative(-113f, -47f)
                    reflectiveQuadToRelative(-113f, 47f)
                    reflectiveQuadToRelative(-47f, 113f)
                    close()
                }
            }.build()
            return _Notifications!!
        }

    private var _Notifications: ImageVector? = null

     val Sunny: ImageVector
        get() {
            if (_Sunny != null) {
                return _Sunny!!
            }
            _Sunny = ImageVector.Builder(
                name = "Sunny",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(440f, 200f)
                    verticalLineToRelative(-160f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(160f)
                    close()
                    moveToRelative(266f, 110f)
                    lineToRelative(-55f, -55f)
                    lineToRelative(112f, -115f)
                    lineToRelative(56f, 57f)
                    close()
                    moveToRelative(54f, 210f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(80f)
                    close()
                    moveTo(440f, 920f)
                    verticalLineToRelative(-160f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(160f)
                    close()
                    moveTo(254f, 308f)
                    lineTo(140f, 197f)
                    lineToRelative(57f, -56f)
                    lineToRelative(113f, 113f)
                    close()
                    moveToRelative(508f, 512f)
                    lineTo(651f, 705f)
                    lineToRelative(54f, -54f)
                    lineToRelative(114f, 110f)
                    close()
                    moveTo(40f, 520f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(157f, 300f)
                    lineToRelative(-56f, -57f)
                    lineToRelative(112f, -112f)
                    lineToRelative(29f, 27f)
                    lineToRelative(29f, 28f)
                    close()
                    moveToRelative(283f, -100f)
                    quadToRelative(-100f, 0f, -170f, -70f)
                    reflectiveQuadToRelative(-70f, -170f)
                    reflectiveQuadToRelative(70f, -170f)
                    reflectiveQuadToRelative(170f, -70f)
                    reflectiveQuadToRelative(170f, 70f)
                    reflectiveQuadToRelative(70f, 170f)
                    reflectiveQuadToRelative(-70f, 170f)
                    reflectiveQuadToRelative(-170f, 70f)
                    moveToRelative(0f, -80f)
                    quadToRelative(66f, 0f, 113f, -47f)
                    reflectiveQuadToRelative(47f, -113f)
                    reflectiveQuadToRelative(-47f, -113f)
                    reflectiveQuadToRelative(-113f, -47f)
                    reflectiveQuadToRelative(-113f, 47f)
                    reflectiveQuadToRelative(-47f, 113f)
                    reflectiveQuadToRelative(47f, 113f)
                    reflectiveQuadToRelative(113f, 47f)
                    moveToRelative(0f, -160f)
                }
            }.build()
            return _Sunny!!
        }

    private var _Sunny: ImageVector? = null


    val ImageUp: ImageVector
        get() {
            if (_ImageUp != null) {
                return _ImageUp!!
            }
            _ImageUp = ImageVector.Builder(
                name = "ImageUp",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(10.3f, 21f)
                    horizontalLineTo(5f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, -2f, -2f)
                    verticalLineTo(5f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -2f)
                    horizontalLineToRelative(14f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 2f)
                    verticalLineToRelative(10f)
                    lineToRelative(-3.1f, -3.1f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.814f, 0.014f)
                    lineTo(6f, 21f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(14f, 19.5f)
                    lineToRelative(3f, -3f)
                    lineToRelative(3f, 3f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(17f, 22f)
                    verticalLineToRelative(-5.5f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(11f, 9f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 11f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 7f, 9f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 11f, 9f)
                    close()
                }
            }.build()
            return _ImageUp!!
        }

    private var _ImageUp: ImageVector? = null


    val Calendar: ImageVector
        get() {
            if (_Calendar != null) {
                return _Calendar!!
            }
            _Calendar = ImageVector.Builder(
                name = "Calendar",
                defaultWidth = 16.dp,
                defaultHeight = 16.dp,
                viewportWidth = 16f,
                viewportHeight = 16f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(14f, 0f)
                    horizontalLineTo(2f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, 2f)
                    verticalLineToRelative(12f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, 2f)
                    horizontalLineToRelative(12f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 2f, -2f)
                    verticalLineTo(2f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2f, -2f)
                    moveTo(1f, 3.857f)
                    curveTo(1f, 3.384f, 1.448f, 3f, 2f, 3f)
                    horizontalLineToRelative(12f)
                    curveToRelative(0.552f, 0f, 1f, 0.384f, 1f, 0.857f)
                    verticalLineToRelative(10.286f)
                    curveToRelative(0f, 0.473f, -0.448f, 0.857f, -1f, 0.857f)
                    horizontalLineTo(2f)
                    curveToRelative(-0.552f, 0f, -1f, -0.384f, -1f, -0.857f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(6.5f, 7f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(-9f, 3f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(-9f, 3f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                    moveToRelative(3f, 0f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = true, isPositiveArc = false, 0f, -2f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0f, 2f)
                }
            }.build()
            return _Calendar!!
        }

    private var _Calendar: ImageVector? = null


    val UserMinus: ImageVector
        get() {
            if (_UserMinus != null) {
                return _UserMinus!!
            }
            _UserMinus = ImageVector.Builder(
                name = "UserMinus",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(16f, 21f)
                    verticalLineToRelative(-2f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, -4f)
                    horizontalLineTo(6f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, 4f)
                    verticalLineToRelative(2f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(13f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 11f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13f, 7f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(22f, 11f)
                    lineTo(16f, 11f)
                }
            }.build()
            return _UserMinus!!
        }

    private var _UserMinus: ImageVector? = null


     val UserPlus: ImageVector
        get() {
            if (_UserPlus != null) {
                return _UserPlus!!
            }
            _UserPlus = ImageVector.Builder(
                name = "UserPlus",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(16f, 21f)
                    verticalLineToRelative(-2f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, -4f)
                    horizontalLineTo(6f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, 4f)
                    verticalLineToRelative(2f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(13f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 11f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13f, 7f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(19f, 8f)
                    lineTo(19f, 14f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(22f, 11f)
                    lineTo(16f, 11f)
                }
            }.build()
            return _UserPlus!!
        }

    private var _UserPlus: ImageVector? = null

     val UserPen: ImageVector
        get() {
            if (_UserPen != null) {
                return _UserPen!!
            }
            _UserPen = ImageVector.Builder(
                name = "UserPen",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(11.5f, 15f)
                    horizontalLineTo(7f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, 4f)
                    verticalLineToRelative(2f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(21.378f, 16.626f)
                    arcToRelative(1f, 1f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.004f, -3.004f)
                    lineToRelative(-4.01f, 4.012f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.506f, 0.854f)
                    lineToRelative(-0.837f, 2.87f)
                    arcToRelative(0.5f, 0.5f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.62f, 0.62f)
                    lineToRelative(2.87f, -0.837f)
                    arcToRelative(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = false, 0.854f, -0.506f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(14f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 11f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 6f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14f, 7f)
                    close()
                }
            }.build()
            return _UserPen!!
        }

    private var _UserPen: ImageVector? = null


    val Users: ImageVector
        get() {
            if (_Users != null) {
                return _Users!!
            }
            _Users = ImageVector.Builder(
                name = "Users",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(16f, 21f)
                    verticalLineToRelative(-2f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, -4f)
                    horizontalLineTo(6f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -4f, 4f)
                    verticalLineToRelative(2f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(13f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9f, 11f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 7f)
                    arcTo(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13f, 7f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(22f, 21f)
                    verticalLineToRelative(-2f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3f, -3.87f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(16f, 3.13f)
                    arcToRelative(4f, 4f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 7.75f)
                }
            }.build()
            return _Users!!
        }

    private var _Users: ImageVector? = null


    val UserRoundCog: ImageVector
        get() {
            if (_UserRoundCog != null) {
                return _UserRoundCog!!
            }
            _UserRoundCog = ImageVector.Builder(
                name = "UserRoundCog",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(2f, 21f)
                    arcToRelative(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10.434f, -7.62f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(15f, 8f)
                    arcTo(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 10f, 13f)
                    arcTo(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5f, 8f)
                    arcTo(5f, 5f, 0f, isMoreThanHalf = false, isPositiveArc = true, 15f, 8f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(21f, 18f)
                    arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 18f, 21f)
                    arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 15f, 18f)
                    arcTo(3f, 3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 21f, 18f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(19.5f, 14.3f)
                    lineToRelative(-0.4f, 0.9f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(16.9f, 20.8f)
                    lineToRelative(-0.4f, 0.9f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(21.7f, 19.5f)
                    lineToRelative(-0.9f, -0.4f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(15.2f, 16.9f)
                    lineToRelative(-0.9f, -0.4f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(21.7f, 16.5f)
                    lineToRelative(-0.9f, 0.4f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(15.2f, 19.1f)
                    lineToRelative(-0.9f, 0.4f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(19.5f, 21.7f)
                    lineToRelative(-0.4f, -0.9f)
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF000000)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(16.9f, 15.2f)
                    lineToRelative(-0.4f, -0.9f)
                }
            }.build()
            return _UserRoundCog!!
        }

   var _UserRoundCog: ImageVector? = null
//events
   val SymbolEvent: ImageVector
        get() {
            if (_SymbolEvent != null) {
                return _SymbolEvent!!
            }
            _SymbolEvent = ImageVector.Builder(
                name = "SymbolEvent",
                defaultWidth = 16.dp,
                defaultHeight = 16.dp,
                viewportWidth = 16f,
                viewportHeight = 16f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(7.414f, 1.56f)
                    lineTo(8.312f, 1f)
                    horizontalLineToRelative(3.294f)
                    lineToRelative(0.818f, 1.575f)
                    lineTo(10.236f, 6f)
                    horizontalLineToRelative(1.781f)
                    lineToRelative(0.72f, 1.695f)
                    lineTo(5.618f, 15f)
                    lineToRelative(-1.602f, -1.163f)
                    lineTo(6.119f, 10f)
                    horizontalLineTo(4.898f)
                    lineTo(4f, 8.56f)
                    lineToRelative(3.414f, -7f)
                    close()
                    moveTo(7.78f, 9f)
                    lineTo(4.9f, 14.305f)
                    lineTo(12.018f, 7f)
                    horizontalLineTo(8.312f)
                    lineToRelative(3.294f, -5f)
                    horizontalLineTo(8.312f)
                    lineTo(4.898f, 9f)
                    horizontalLineTo(7.78f)
                    close()
                }
            }.build()
            return _SymbolEvent!!
        }

    private var _SymbolEvent: ImageVector? = null

     val Event_list: ImageVector
        get() {
            if (_Event_list != null) {
                return _Event_list!!
            }
            _Event_list = ImageVector.Builder(
                name = "Event_list",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(640f, 840f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(560f, 760f)
                    verticalLineToRelative(-160f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(640f, 520f)
                    horizontalLineToRelative(160f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(880f, 600f)
                    verticalLineToRelative(160f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(800f, 840f)
                    close()
                    moveToRelative(0f, -80f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(-160f)
                    horizontalLineTo(640f)
                    close()
                    moveTo(80f, 720f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(360f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(560f, -280f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(560f, 360f)
                    verticalLineToRelative(-160f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(640f, 120f)
                    horizontalLineToRelative(160f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(880f, 200f)
                    verticalLineToRelative(160f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(800f, 440f)
                    close()
                    moveToRelative(0f, -80f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(-160f)
                    horizontalLineTo(640f)
                    close()
                    moveTo(80f, 320f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(360f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(640f, -40f)
                }
            }.build()
            return _Event_list!!
        }

    private var _Event_list: ImageVector? = null

    val Event: ImageVector
        get() {
            if (_Event != null) {
                return _Event!!
            }
            _Event = ImageVector.Builder(
                name = "Event",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(580f, 720f)
                    quadToRelative(-42f, 0f, -71f, -29f)
                    reflectiveQuadToRelative(-29f, -71f)
                    reflectiveQuadToRelative(29f, -71f)
                    reflectiveQuadToRelative(71f, -29f)
                    reflectiveQuadToRelative(71f, 29f)
                    reflectiveQuadToRelative(29f, 71f)
                    reflectiveQuadToRelative(-29f, 71f)
                    reflectiveQuadToRelative(-71f, 29f)
                    moveTo(200f, 880f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(120f, 800f)
                    verticalLineToRelative(-560f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(200f, 160f)
                    horizontalLineToRelative(40f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(80f)
                    horizontalLineToRelative(320f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(80f)
                    horizontalLineToRelative(40f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(840f, 240f)
                    verticalLineToRelative(560f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(760f, 880f)
                    close()
                    moveToRelative(0f, -80f)
                    horizontalLineToRelative(560f)
                    verticalLineToRelative(-400f)
                    horizontalLineTo(200f)
                    close()
                    moveToRelative(0f, -480f)
                    horizontalLineToRelative(560f)
                    verticalLineToRelative(-80f)
                    horizontalLineTo(200f)
                    close()
                    moveToRelative(0f, 0f)
                    verticalLineToRelative(-80f)
                    close()
                }
            }.build()
            return _Event!!
        }

    private var _Event: ImageVector? = null
      val Event_upcoming: ImageVector
        get() {
            if (_Event_upcoming != null) {
                return _Event_upcoming!!
            }
            _Event_upcoming = ImageVector.Builder(
                name = "Event_upcoming",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(600f, 880f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(-400f)
                    horizontalLineTo(200f)
                    verticalLineToRelative(160f)
                    horizontalLineToRelative(-80f)
                    verticalLineToRelative(-320f)
                    quadToRelative(0f, -33f, 23.5f, -56.5f)
                    reflectiveQuadTo(200f, 160f)
                    horizontalLineToRelative(40f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(80f)
                    horizontalLineToRelative(320f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(80f)
                    horizontalLineToRelative(40f)
                    quadToRelative(33f, 0f, 56.5f, 23.5f)
                    reflectiveQuadTo(840f, 240f)
                    verticalLineToRelative(560f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(760f, 880f)
                    close()
                    moveTo(320f, 960f)
                    lineToRelative(-56f, -56f)
                    lineToRelative(103f, -104f)
                    horizontalLineTo(40f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(327f)
                    lineTo(264f, 616f)
                    lineToRelative(56f, -56f)
                    lineToRelative(200f, 200f)
                    close()
                    moveTo(200f, 320f)
                    horizontalLineToRelative(560f)
                    verticalLineToRelative(-80f)
                    horizontalLineTo(200f)
                    close()
                    moveToRelative(0f, 0f)
                    verticalLineToRelative(-80f)
                    close()
                }
            }.build()
            return _Event_upcoming!!
        }

    private var _Event_upcoming: ImageVector? = null


    //income
    val Finance: ImageVector
        get() {
            if (_Finance != null) {
                return _Finance!!
            }
            _Finance = ImageVector.Builder(
                name = "Finance",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(200f, 840f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(120f, 760f)
                    verticalLineToRelative(-640f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(640f)
                    horizontalLineToRelative(640f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(40f, -120f)
                    verticalLineToRelative(-360f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(360f)
                    close()
                    moveToRelative(200f, 0f)
                    verticalLineToRelative(-560f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(560f)
                    close()
                    moveToRelative(200f, 0f)
                    verticalLineToRelative(-200f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(200f)
                    close()
                }
            }.build()
            return _Finance!!
        }

    private var _Finance: ImageVector? = null

    val Finance_mode: ImageVector
        get() {
            if (_Finance_mode != null) {
                return _Finance_mode!!
            }
            _Finance_mode = ImageVector.Builder(
                name = "Finance_mode",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(320f, 546f)
                    verticalLineToRelative(-306f)
                    horizontalLineToRelative(120f)
                    verticalLineToRelative(306f)
                    lineToRelative(-60f, -56f)
                    close()
                    moveToRelative(200f, 60f)
                    verticalLineToRelative(-526f)
                    horizontalLineToRelative(120f)
                    verticalLineToRelative(406f)
                    close()
                    moveTo(120f, 744f)
                    verticalLineToRelative(-344f)
                    horizontalLineToRelative(120f)
                    verticalLineToRelative(224f)
                    close()
                    moveToRelative(0f, 98f)
                    lineToRelative(258f, -258f)
                    lineToRelative(142f, 122f)
                    lineToRelative(224f, -224f)
                    horizontalLineToRelative(-64f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(200f)
                    verticalLineToRelative(200f)
                    horizontalLineToRelative(-80f)
                    verticalLineToRelative(-64f)
                    lineTo(524f, 814f)
                    lineTo(382f, 692f)
                    lineTo(232f, 842f)
                    close()
                }
            }.build()
            return _Finance_mode!!
        }

    private var _Finance_mode: ImageVector? = null

     val Equalizer: ImageVector
        get() {
            if (_Equalizer != null) {
                return _Equalizer!!
            }
            _Equalizer = ImageVector.Builder(
                name = "Equalizer",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(160f, 800f)
                    verticalLineToRelative(-320f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(320f)
                    close()
                    moveToRelative(240f, 0f)
                    verticalLineToRelative(-640f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(640f)
                    close()
                    moveToRelative(240f, 0f)
                    verticalLineToRelative(-440f)
                    horizontalLineToRelative(160f)
                    verticalLineToRelative(440f)
                    close()
                }
            }.build()
            return _Equalizer!!
        }

    private var _Equalizer: ImageVector? = null
   val Add: ImageVector
        get() {
            if (_Add != null) {
                return _Add!!
            }
            _Add = ImageVector.Builder(
                name = "Add",
                defaultWidth = 16.dp,
                defaultHeight = 16.dp,
                viewportWidth = 16f,
                viewportHeight = 16f
            ).apply {
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(14f, 7f)
                    verticalLineToRelative(1f)
                    horizontalLineTo(8f)
                    verticalLineToRelative(6f)
                    horizontalLineTo(7f)
                    verticalLineTo(8f)
                    horizontalLineTo(1f)
                    verticalLineTo(7f)
                    horizontalLineToRelative(6f)
                    verticalLineTo(1f)
                    horizontalLineToRelative(1f)
                    verticalLineToRelative(6f)
                    horizontalLineToRelative(6f)
                    close()
                }
            }.build()
            return _Add!!
        }

    private var _Add: ImageVector? = null
     val Data_usage: ImageVector
        get() {
            if (_Data_usage != null) {
                return _Data_usage!!
            }
            _Data_usage = ImageVector.Builder(
                name = "Data_usage",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(480f, 880f)
                    quadToRelative(-83f, 0f, -156f, -31.5f)
                    reflectiveQuadToRelative(-127f, -86f)
                    reflectiveQuadTo(111.5f, 635f)
                    reflectiveQuadTo(80f, 480f)
                    quadToRelative(0f, -157f, 104f, -270f)
                    reflectiveQuadToRelative(256f, -128f)
                    verticalLineToRelative(120f)
                    quadToRelative(-103f, 14f, -171.5f, 92.5f)
                    reflectiveQuadTo(200f, 480f)
                    quadToRelative(0f, 116f, 82f, 198f)
                    reflectiveQuadToRelative(198f, 82f)
                    quadToRelative(66f, 0f, 123.5f, -28f)
                    reflectiveQuadToRelative(96.5f, -76f)
                    lineToRelative(104f, 60f)
                    quadToRelative(-54f, 75f, -139f, 119.5f)
                    reflectiveQuadTo(480f, 880f)
                    moveToRelative(366f, -238f)
                    lineToRelative(-104f, -60f)
                    quadToRelative(9f, -24f, 13.5f, -49.5f)
                    reflectiveQuadTo(760f, 480f)
                    quadToRelative(0f, -107f, -68.5f, -185.5f)
                    reflectiveQuadTo(520f, 202f)
                    verticalLineToRelative(-120f)
                    quadToRelative(152f, 15f, 256f, 128f)
                    reflectiveQuadToRelative(104f, 270f)
                    quadToRelative(0f, 44f, -8f, 85f)
                    reflectiveQuadToRelative(-26f, 77f)
                }
            }.build()
            return _Data_usage!!
        }

    private var _Data_usage: ImageVector? = null
     val Density_medium: ImageVector
        get() {
            if (_Density_medium != null) {
                return _Density_medium!!
            }
            _Density_medium = ImageVector.Builder(
                name = "Density_medium",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(120f, 840f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(720f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(0f, -320f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(720f)
                    verticalLineToRelative(80f)
                    close()
                    moveToRelative(0f, -320f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(720f)
                    verticalLineToRelative(80f)
                    close()
                }
            }.build()
            return _Density_medium!!
        }

    private var _Density_medium: ImageVector? = null
   val Delete: ImageVector
        get() {
            if (_Delete != null) {
                return _Delete!!
            }
            _Delete = ImageVector.Builder(
                name = "Delete",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(280f, 840f)
                    quadToRelative(-33f, 0f, -56.5f, -23.5f)
                    reflectiveQuadTo(200f, 760f)
                    verticalLineToRelative(-520f)
                    horizontalLineToRelative(-40f)
                    verticalLineToRelative(-80f)
                    horizontalLineToRelative(200f)
                    verticalLineToRelative(-40f)
                    horizontalLineToRelative(240f)
                    verticalLineToRelative(40f)
                    horizontalLineToRelative(200f)
                    verticalLineToRelative(80f)
                    horizontalLineToRelative(-40f)
                    verticalLineToRelative(520f)
                    quadToRelative(0f, 33f, -23.5f, 56.5f)
                    reflectiveQuadTo(680f, 840f)
                    close()
                    moveToRelative(400f, -600f)
                    horizontalLineTo(280f)
                    verticalLineToRelative(520f)
                    horizontalLineToRelative(400f)
                    close()
                    moveTo(360f, 680f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(-360f)
                    horizontalLineToRelative(-80f)
                    close()
                    moveToRelative(160f, 0f)
                    horizontalLineToRelative(80f)
                    verticalLineToRelative(-360f)
                    horizontalLineToRelative(-80f)
                    close()
                    moveTo(280f, 240f)
                    verticalLineToRelative(520f)
                    close()
                }
            }.build()
            return _Delete!!
        }

    private var _Delete: ImageVector? = null

    val Settings: ImageVector
        get() {
            if (_Settings != null) {
                return _Settings!!
            }
            _Settings = ImageVector.Builder(
                name = "Settings",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(370f, 880f)
                    lineToRelative(-16f, -128f)
                    quadToRelative(-13f, -5f, -24.5f, -12f)
                    reflectiveQuadTo(307f, 725f)
                    lineToRelative(-119f, 50f)
                    lineTo(78f, 585f)
                    lineToRelative(103f, -78f)
                    quadToRelative(-1f, -7f, -1f, -13.5f)
                    verticalLineToRelative(-27f)
                    quadToRelative(0f, -6.5f, 1f, -13.5f)
                    lineTo(78f, 375f)
                    lineToRelative(110f, -190f)
                    lineToRelative(119f, 50f)
                    quadToRelative(11f, -8f, 23f, -15f)
                    reflectiveQuadToRelative(24f, -12f)
                    lineToRelative(16f, -128f)
                    horizontalLineToRelative(220f)
                    lineToRelative(16f, 128f)
                    quadToRelative(13f, 5f, 24.5f, 12f)
                    reflectiveQuadToRelative(22.5f, 15f)
                    lineToRelative(119f, -50f)
                    lineToRelative(110f, 190f)
                    lineToRelative(-103f, 78f)
                    quadToRelative(1f, 7f, 1f, 13.5f)
                    verticalLineToRelative(27f)
                    quadToRelative(0f, 6.5f, -2f, 13.5f)
                    lineToRelative(103f, 78f)
                    lineToRelative(-110f, 190f)
                    lineToRelative(-118f, -50f)
                    quadToRelative(-11f, 8f, -23f, 15f)
                    reflectiveQuadToRelative(-24f, 12f)
                    lineTo(590f, 880f)
                    close()
                    moveToRelative(70f, -80f)
                    horizontalLineToRelative(79f)
                    lineToRelative(14f, -106f)
                    quadToRelative(31f, -8f, 57.5f, -23.5f)
                    reflectiveQuadTo(639f, 633f)
                    lineToRelative(99f, 41f)
                    lineToRelative(39f, -68f)
                    lineToRelative(-86f, -65f)
                    quadToRelative(5f, -14f, 7f, -29.5f)
                    reflectiveQuadToRelative(2f, -31.5f)
                    reflectiveQuadToRelative(-2f, -31.5f)
                    reflectiveQuadToRelative(-7f, -29.5f)
                    lineToRelative(86f, -65f)
                    lineToRelative(-39f, -68f)
                    lineToRelative(-99f, 42f)
                    quadToRelative(-22f, -23f, -48.5f, -38.5f)
                    reflectiveQuadTo(533f, 266f)
                    lineToRelative(-13f, -106f)
                    horizontalLineToRelative(-79f)
                    lineToRelative(-14f, 106f)
                    quadToRelative(-31f, 8f, -57.5f, 23.5f)
                    reflectiveQuadTo(321f, 327f)
                    lineToRelative(-99f, -41f)
                    lineToRelative(-39f, 68f)
                    lineToRelative(86f, 64f)
                    quadToRelative(-5f, 15f, -7f, 30f)
                    reflectiveQuadToRelative(-2f, 32f)
                    quadToRelative(0f, 16f, 2f, 31f)
                    reflectiveQuadToRelative(7f, 30f)
                    lineToRelative(-86f, 65f)
                    lineToRelative(39f, 68f)
                    lineToRelative(99f, -42f)
                    quadToRelative(22f, 23f, 48.5f, 38.5f)
                    reflectiveQuadTo(427f, 694f)
                    close()
                    moveToRelative(42f, -180f)
                    quadToRelative(58f, 0f, 99f, -41f)
                    reflectiveQuadToRelative(41f, -99f)
                    reflectiveQuadToRelative(-41f, -99f)
                    reflectiveQuadToRelative(-99f, -41f)
                    quadToRelative(-59f, 0f, -99.5f, 41f)
                    reflectiveQuadTo(342f, 480f)
                    reflectiveQuadToRelative(40.5f, 99f)
                    reflectiveQuadToRelative(99.5f, 41f)
                    moveToRelative(-2f, -140f)
                }
            }.build()
            return _Settings!!
        }

    private var _Settings: ImageVector? = null


    val ChartPie: ImageVector
        get() {
            if (_ChartPie != null) {
                return _ChartPie!!
            }
            _ChartPie = ImageVector.Builder(
                name = "ChartPie",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 24f,
                viewportHeight = 24f
            ).apply {
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF0F172A)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.5f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(10.5f, 6f)
                    curveTo(6.3579f, 6f, 3f, 9.3579f, 3f, 13.5f)
                    curveTo(3f, 17.6421f, 6.3579f, 21f, 10.5f, 21f)
                    curveTo(14.6421f, 21f, 18f, 17.6421f, 18f, 13.5f)
                    horizontalLineTo(10.5f)
                    verticalLineTo(6f)
                    close()
                }
                path(
                    fill = null,
                    fillAlpha = 1.0f,
                    stroke = SolidColor(Color(0xFF0F172A)),
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.5f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(13.5f, 10.5f)
                    horizontalLineTo(21f)
                    curveTo(21f, 6.3579f, 17.6421f, 3f, 13.5f, 3f)
                    verticalLineTo(10.5f)
                    close()
                }
            }.build()
            return _ChartPie!!
        }

    private var _ChartPie: ImageVector? = null

   val Construction: ImageVector
        get() {
            if (_Construction != null) {
                return _Construction!!
            }
            _Construction = ImageVector.Builder(
                name = "Construction",
                defaultWidth = 24.dp,
                defaultHeight = 24.dp,
                viewportWidth = 960f,
                viewportHeight = 960f
            ).apply {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(756f, 840f)
                    lineTo(537f, 621f)
                    lineToRelative(84f, -84f)
                    lineToRelative(219f, 219f)
                    close()
                    moveToRelative(-552f, 0f)
                    lineToRelative(-84f, -84f)
                    lineToRelative(276f, -276f)
                    lineToRelative(-68f, -68f)
                    lineToRelative(-28f, 28f)
                    lineToRelative(-51f, -51f)
                    verticalLineToRelative(82f)
                    lineToRelative(-28f, 28f)
                    lineToRelative(-121f, -121f)
                    lineToRelative(28f, -28f)
                    horizontalLineToRelative(82f)
                    lineToRelative(-50f, -50f)
                    lineToRelative(142f, -142f)
                    quadToRelative(20f, -20f, 43f, -29f)
                    reflectiveQuadToRelative(47f, -9f)
                    reflectiveQuadToRelative(47f, 9f)
                    reflectiveQuadToRelative(43f, 29f)
                    lineToRelative(-92f, 92f)
                    lineToRelative(50f, 50f)
                    lineToRelative(-28f, 28f)
                    lineToRelative(68f, 68f)
                    lineToRelative(90f, -90f)
                    quadToRelative(-4f, -11f, -6.5f, -23f)
                    reflectiveQuadToRelative(-2.5f, -24f)
                    quadToRelative(0f, -59f, 40.5f, -99.5f)
                    reflectiveQuadTo(701f, 119f)
                    quadToRelative(15f, 0f, 28.5f, 3f)
                    reflectiveQuadToRelative(27.5f, 9f)
                    lineToRelative(-99f, 99f)
                    lineToRelative(72f, 72f)
                    lineToRelative(99f, -99f)
                    quadToRelative(7f, 14f, 9.5f, 27.5f)
                    reflectiveQuadTo(841f, 259f)
                    quadToRelative(0f, 59f, -40.5f, 99.5f)
                    reflectiveQuadTo(701f, 399f)
                    quadToRelative(-12f, 0f, -24f, -2f)
                    reflectiveQuadToRelative(-23f, -7f)
                    close()
                }
            }.build()
            return _Construction!!
        }

    private var _Construction: ImageVector? = null







}
