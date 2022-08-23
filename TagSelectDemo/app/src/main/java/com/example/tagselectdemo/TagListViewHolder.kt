package com.example.tagselectdemo

import androidx.recyclerview.widget.RecyclerView
import com.example.tagselectdemo.databinding.RowAdapterTagListBinding

class TagListViewHolder (var binding: RowAdapterTagListBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(tag:String){
        binding.tvTag.text = tag
    }

}