package ru.maxultra.developerslife.util

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ru.maxultra.developerslife.R

fun getTabText(position: Int, resources: Resources): String {
    return when (position) {
        0 -> resources.getString(R.string.random)
        1 -> resources.getString(R.string.latest)
        2 -> resources.getString(R.string.top)
        else -> throw IllegalStateException("Unknown tab (number $position)")
    }
}

private lateinit var loadingDrawable: Drawable

fun getLoadingDrawable(context: Context): Drawable {
    if (!::loadingDrawable.isInitialized) {
        val drawable = CircularProgressDrawable(context)
        drawable.centerRadius = 40f
        drawable.strokeWidth = 10f
        drawable.setColorSchemeColors(context.getColor(R.color.peter_river))
        drawable.start()
        loadingDrawable = drawable
    }
    return loadingDrawable
}
