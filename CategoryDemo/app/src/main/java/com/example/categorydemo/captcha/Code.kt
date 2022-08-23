package com.example.categorydemo.captcha

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.lang.StringBuilder
import java.util.*


class Code {
    //settings decided by the layout xml
    //canvas width and height
    private val width = DEFAULT_WIDTH
    private val height = DEFAULT_HEIGHT

    //random word space and pading_top
    private val base_padding_left = BASE_PADDING_LEFT
    private val range_padding_left = RANGE_PADDING_LEFT
    private val base_padding_top = BASE_PADDING_TOP
    private val range_padding_top = RANGE_PADDING_TOP

    //number of chars, lines; font size
    private val codeLength = DEFAULT_CODE_LENGTH
    private val line_number = DEFAULT_LINE_NUMBER
    private val font_size = DEFAULT_FONT_SIZE

    //variables
    var code: String? = null
        private set
    private var padding_left = 0
    private var padding_top = 0
    private val random = Random()

    //captcha image
    fun createBitmap(): Bitmap {
        padding_left = 0
        val bp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888) //bitmap clarity
        val c = Canvas(bp)
        code = createCode()
        c.drawColor(Color.GREEN) //background color
        val paint = Paint()
        paint.isAntiAlias = true
        paint.textSize = font_size.toFloat()
        //draw code
        for (i in 0 until code!!.length) {
            randomTextStyle(paint)
            randomPadding()
            c.drawText(
                code!![i].toString() + "",
                padding_left.toFloat(),
                padding_top.toFloat(),
                paint
            )
        }
        //draw line
        for (i in 0 until line_number) {
            drawLine(c, paint)
        }
        c.save() //save
        c.restore() //
        return bp
    }

    //generate code
    private fun createCode(): String {
        val buffer = StringBuilder()
        for (i in 0 until codeLength) {
            buffer.append(CHARS[random.nextInt(CHARS.size)])
        }
        return buffer.toString()
    }

    //draw the line
    private fun drawLine(canvas: Canvas, paint: Paint) {
        val color = randomColor()
        val startX = random.nextInt(width)
        val startY = random.nextInt(height)
        val stopX = random.nextInt(width)
        val stopY = random.nextInt(height)
        paint.strokeWidth = 1f
        paint.color = color
        canvas.drawLine(startX.toFloat(), startY.toFloat(), stopX.toFloat(), stopY.toFloat(), paint)
    }

    //generate random color
    private fun randomColor(rate: Int = 1): Int {
        val red = random.nextInt(256) / rate
        val green = random.nextInt(256) / rate
        val blue = random.nextInt(256) / rate
        return Color.rgb(red, green, blue)
    }

    //random generate text style, font, color, inclination
    private fun randomTextStyle(paint: Paint) {
        val color = Color.WHITE//randomColor()
        paint.color = color
        paint.isFakeBoldText = random.nextBoolean() //true==bold，false==normal
//        var skewX = (random.nextInt(11) / 10).toFloat()
//        skewX = if (random.nextBoolean()) skewX else -skewX
//        paint.textSkewX = skewX //float，nagivate be right，int be left
        //paint.setUnderlineText(true); //true: return underline
        //paint.setStrikeThruText(true); //true: return strike line
    }

    //random padding number
    private fun randomPadding() {
        padding_left += base_padding_left + random.nextInt(range_padding_left)
        padding_top = base_padding_top + random.nextInt(range_padding_top)
    }

    companion object {
        //random array
        private val CHARS = charArrayOf(
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm',
            'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
        )
        private var bmpCode: Code? = null
        val instance: Code?
            get() {
                if (bmpCode == null) bmpCode = Code()
                return bmpCode
            }

        //default settings
        //captcha default length
        private const val DEFAULT_CODE_LENGTH = 5

        //captcha default text size
        private const val DEFAULT_FONT_SIZE = 28

        //captcha default line number
        private const val DEFAULT_LINE_NUMBER = 2

        //padding
        private const val BASE_PADDING_LEFT = 25
        private const val RANGE_PADDING_LEFT = 10
        private const val BASE_PADDING_TOP = 20
        private const val RANGE_PADDING_TOP = 20

        //captcha width and height
        private const val DEFAULT_WIDTH = 240
        private const val DEFAULT_HEIGHT = 50
    }
}