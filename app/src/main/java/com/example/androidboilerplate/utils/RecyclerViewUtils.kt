package com.example.androidboilerplate.utils

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("content")
fun <T> RecyclerView.submitList(content: List<T>?) {
    content?.let {
        @Suppress("UNCHECKED_CAST")
        val adapter = adapter as ListAdapter<T, *>
        adapter.submitList(it)
    }
}