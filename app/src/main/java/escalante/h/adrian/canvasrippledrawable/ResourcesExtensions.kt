package escalante.h.adrian.canvasrippledrawable

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt

@ColorInt
fun Resources.getAttributeColor(@AttrRes colorId: Int, context: Context): Int {
    val typedValue = TypedValue()
    context.theme.resolveAttribute(colorId, typedValue, true)
    return typedValue.data
}