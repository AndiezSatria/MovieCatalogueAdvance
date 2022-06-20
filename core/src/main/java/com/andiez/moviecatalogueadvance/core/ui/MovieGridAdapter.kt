package com.andiez.moviecatalogueadvance.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andiez.moviecatalogueadvance.core.databinding.LayoutMovieNormalBinding
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem

class MovieGridAdapter : ListAdapter<ShowItem, MovieGridAdapter.ViewHolder>(DIFF_CALLBACK) {
    var onItemClick: ((ShowItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutMovieNormalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
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