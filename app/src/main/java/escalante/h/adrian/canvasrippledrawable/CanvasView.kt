package escalante.h.adrian.canvasrippledrawable

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import com.google.android.material.R.attr

private const val PRESSED_STATE = android.R.attr.state_pressed
private const val ENABLED_STATE = android.R.attr.state_enabled

class CanvasView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val rippleDrawable: RippleDrawable

    init {
        val rippleColor = resources.getAttributeColor(attr.colorControlHighlight, context)
        val mask = ColorDrawable(Color.WHITE)
        rippleDrawable = RippleDrawable(ColorStateList.valueOf(rippleColor), null, mask)
        rippleDrawable.state = intArrayOf(ENABLED_STATE)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            ACTION_DOWN -> {
                rippleDrawable.setHotspot(event.x, event.y)
                rippleDrawable.state = intArrayOf(ENABLED_STATE, PRESSED_STATE)
                invalidate()
            }
            ACTION_UP -> {
                rippleDrawable.state = intArrayOf(ENABLED_STATE)
                performClick()
                invalidate()
            }
            ACTION_CANCEL -> {
                rippleDrawable.state = intArrayOf(ENABLED_STATE)
                invalidate()
            }
        }

        return true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        rippleDrawable.setBounds(
            0,
            0,
            MeasureSpec.getSize(widthMeasureSpec),
            MeasureSpec.getSize(heightMeasureSpec)
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        rippleDrawable.draw(canvas)
    }
}