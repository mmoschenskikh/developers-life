package ru.maxultra.developerslife

import android.content.res.Resources

fun getTabText(position: Int, resources: Resources): String {
    return when (position) {
        0 -> resources.getString(R.string.random)
        1 -> resources.getString(R.string.latest)
        2 -> resources.getString(R.string.hot)
        3 -> resources.getString(R.string.top)
        else -> throw IllegalStateException("Unknown tab (number $position)")
    }
}
