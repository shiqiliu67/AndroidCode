package com.accenture.categorydemo

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tagselectdemo.databinding.RowAdapterTagListBinding

class TagListDrawerAdapter (private val itemClick: (String) -> Unit) :
    androidx.recyclerview.widget.ListAdapter<String,TagListDrawerAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

    }) {

    inner class ViewHolder(var binding: RowAdapterTagListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(discoverItem: String) {
            discoverItem.let {
                binding.tvTag.text = it //it.getCategoryInfo()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        RowAdapterTagListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    ).apply {
        binding.root.setOnClickListener {
            itemClick(getItem(adapterPosition))
            Log.e(TAG, "onCreateViewHolder: item click: ${getItem(adapterPosition)} ")
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    companion object {
        private val TAG = "CategoryAdapter"
    }
}