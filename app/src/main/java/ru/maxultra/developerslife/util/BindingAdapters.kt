package ru.maxultra.developerslife.util

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ru.maxultra.developerslife.R
import ru.maxultra.developerslife.ui.gifpage.GifApiStatus

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .asGif()
            .apply(
                RequestOptions()
                    .placeholder(getLoadingDrawable(imgView.context))
                    .error(R.mipmap.ic_image_corrupt)
            )
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("errorStatus")
fun bindStatusError(errorView: View, status: GifApiStatus?) {
    when (status) {
        GifApiStatus.ERROR -> errorView.visibility = View.VISIBLE
        else -> errorView.visibility = View.GONE
    }
}

@BindingAdapter("correctStatus")
fun bindStatusCorrect(correctView: View, status: GifApiStatus?) {
    when (status) {
        GifApiStatus.ERROR -> correctView.visibility = View.GONE
        else -> correctView.visibility = View.VISIBLE
    }
}
