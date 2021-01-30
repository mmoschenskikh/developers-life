package ru.maxultra.developerslife

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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
