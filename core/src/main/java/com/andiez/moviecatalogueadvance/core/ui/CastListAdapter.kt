package com.andiez.moviecatalogueadvance.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andiez.moviecatalogueadvance.core.databinding.LayoutListCastBinding
import com.andiez.moviecatalogueadvance.core.presenter.model.CastItem

class CastListAdapter : ListAdapter<CastItem, CastListAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastListAdapter.ViewHolder =
        ViewHolder(
            LayoutListCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CastListAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: LayoutListCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: CastItem) {
            binding.data = data
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CastItem>() {
            override fun areItemsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: CastItem, newItem: CastItem): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

}