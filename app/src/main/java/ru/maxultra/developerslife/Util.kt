package ru.maxultra.developerslife

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

fun getTabText(position: Int, resources: Resources): String {
    return when (position) {
        0 -> resources.getString(R.string.random)
        1 -> resources.getString(R.string.latest)
        2 -> resources.getString(R.string.hot)
        3 -> resources.getString(R.string.top)
        else -> throw IllegalStateException("Unknown tab (number $position)")
    }
}

fun getLoadingDrawable(context: Context): Drawable {
    val drawable = CircularProgressDrawable(context)
    drawable.centerRadius = 40f
    drawable.strokeWidth = 10f
    drawable.setColorSchemeColors(context.getColor(R.color.peter_river))
    drawable.start()
    return drawable
}
