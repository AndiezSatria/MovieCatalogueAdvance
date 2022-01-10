package com.andiez.moviecatalogueadvance.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andiez.moviecatalogueadvance.core.databinding.LayoutMovieGridBinding
import com.andiez.moviecatalogueadvance.core.databinding.LayoutMovieNormalBinding
import com.andiez.moviecatalogueadvance.core.domain.model.Movie
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem
import com.andiez.moviecatalogueadvance.core.utils.MovieViewHolder

class MovieGridAdapter : ListAdapter<ShowItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((ShowItem) -> Unit)? = null
    var viewHolder = MovieViewHolder.Normal
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewHolder) {
            MovieViewHolder.Popular -> PopularViewHolder(
                LayoutMovieGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            MovieViewHolder.Normal -> ViewHolder(
                LayoutMovieNormalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (viewHolder) {
            MovieViewHolder.Popular -> (holder as PopularViewHolder).bind(getItem(position))
            MovieViewHolder.Normal -> (holder as ViewHolder).bind(getItem(position))
        }
    }

    inner class PopularViewHolder(private val binding: LayoutMovieGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ShowItem) {
            with(binding) {
                root.setOnClickListener {
                    onItemClick?.invoke(data)
                }
                this.data = data
            }
        }
    }

    inner class ViewHolder(private val binding: LayoutMovieNormalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ShowItem) {
            with(binding) {
                root.setOnClickListener {
                    onItemClick?.invoke(data)
                }
                this.data = data
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ShowItem>() {
            override fun areItemsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean =
                oldItem === newItem

            override fun areContentsTheSame(oldItem: ShowItem, newItem: ShowItem): Boolean =
                oldItem.id == newItem.id
        }
    }

}