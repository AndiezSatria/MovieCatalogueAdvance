package com.andiez.moviecatalogueadvance.core.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.andiez.moviecatalogueadvance.core.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("bindPoster")
fun bindPoster(imageView: ImageView, posterPath: String?) {
    posterPath?.let {
        val url = "https://image.tmdb.org/t/p/w185$it"
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .override(150, 200)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}

@BindingAdapter("bindLargePoster")
fun bindLargePoster(imageView: ImageView, posterPath: String?) {
    posterPath?.let {
        val url = "https://image.tmdb.org/t/p/original$it"
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imageView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .override(150, 200)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imageView)
    }
}