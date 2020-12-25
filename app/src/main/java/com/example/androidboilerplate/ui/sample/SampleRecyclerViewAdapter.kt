package com.example.androidboilerplate.ui.sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidboilerplate.database.entities.Sample
import com.example.androidboilerplate.databinding.SampleListItemBinding

class SampleRecyclerViewAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Sample, SampleRecyclerViewAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder private constructor(private val binding: SampleListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SampleListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(item: Sample, clickListener: OnClickListener) {
            binding.sample = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    fun interface OnClickListener {
        fun onClick(item: Sample, view: View)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Sample>() {
        override fun areItemsTheSame(oldItem: Sample, newItem: Sample): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Sample, newItem: Sample): Boolean {
            return oldItem.id == newItem.id
        }
    }
}