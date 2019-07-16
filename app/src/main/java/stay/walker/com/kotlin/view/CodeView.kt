package stay.walker.com.kotlin.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.annotation.RequiresApi
import stay.walker.com.retrofitdemo.R
import java.util.*

class CodeView : AppCompatTextView {
    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context) : this(context, null)

    @RequiresApi(Build.VERSION_CODES.M)
    constructor(context: Context, attrs: AttributeSet?):super(context, attrs) {
        setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(getContext().getColor(R.color.e6_grey))
        setTextColor(Color.WHITE)


        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL

        updateCode()
    }

    public fun updateCode() {
        val ramdon = Random().nextInt(codelist.size)
        val code = codelist[ramdon]
        text = code
    }

    private var paint = Paint()
    private var codelist = arrayOf(
            "Hello",
            "Kotlin"
    )


    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0F, height.toFloat(), width.toFloat(), 0.toFloat(), paint)
        super.onDraw(canvas)
    }



}