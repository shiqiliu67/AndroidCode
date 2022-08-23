package com.example.categorydemo.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.categorydemo.data.CategoryData
import com.example.categorydemo.databinding.RowCategoryUnfollowAdapterBinding

class UnfollowCategoryTopicAdapter(private val itemClick: (CategoryData) -> Unit, private val checkedObserver: (CategoryData) ->Unit) :
    androidx.recyclerview.widget.ListAdapter<CategoryData, UnfollowCategoryTopicAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

    }) {

    inner class ViewHolder(var binding: RowCategoryUnfollowAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(discoverItem: CategoryData) {
            discoverItem.let {
                binding.categoriesText.text = it.categoryInfo //it.getCategoryInfo()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        RowCategoryUnfollowAdapterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )
        .apply {
            binding.apply {
                root.setOnClickListener {
                    itemClick(getItem(adapterPosition))
                    Log.e(TAG, "onCreateViewHolder: item click: ${getItem(adapterPosition)} ",)
                }
                categoriesCheckbox.setOnClickListener {
                    //  binding.root.visibility = View.GONE
                    val categoryData = getItem(adapterPosition)
                    delete(adapterPosition)
                    categoryData.isSelected = true
                    checkedObserver(categoryData)
                }
            }
        }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun add(item: CategoryData) {
        submitList(currentList + item)
    }

    fun addList(items: ArrayList<CategoryData>) {
        submitList(currentList + items)
    }

    fun delete(item: Int) {

        var newList = ArrayList<CategoryData>()
        newList.clear()
        newList.addAll(currentList)
        newList.removeAt(item)
        submitList(newList)
    }


    companion object {
        private val TAG = "CategoryAdapter"
    }
}