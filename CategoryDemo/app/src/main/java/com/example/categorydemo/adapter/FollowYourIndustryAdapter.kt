package com.example.categorydemo.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.categorydemo.FollowYourIndustryActivity
import com.example.categorydemo.R
import com.example.categorydemo.data.CategoryData
import com.example.categorydemo.databinding.RowAdapterForYouIndustryBinding
import com.example.categorydemo.databinding.RowFollowCategoryAdapterBinding

class FollowYourIndustryAdapter (private val rootClick: (CategoryData) -> Unit, private val checkedObserver: (CategoryData) ->Unit):
    androidx.recyclerview.widget.ListAdapter<CategoryData, FollowYourIndustryAdapter.ViewHolder>(object :
    DiffUtil.ItemCallback<CategoryData>(){
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

    }){
    lateinit var mContext: Context

    inner class ViewHolder(var binding: RowAdapterForYouIndustryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val checkBox = binding.checkbox
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(discoverItem: CategoryData) {
            discoverItem.let {
                binding.tvIndustry.text = it.categoryInfo //it.getCategoryInfo()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder= ViewHolder(
       RowAdapterForYouIndustryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )  .apply {
        mContext = parent.context
        binding.apply {
            root.setOnClickListener {
                rootClick(getItem(adapterPosition))
                Log.e(TAG, "onCreateViewHolder: item click: ${getItem(adapterPosition)} ", )
            }
            checkbox.setOnClickListener {
                val categoryData = getItem(adapterPosition)
                categoryData.isSelected = checkbox.isChecked
                checkedObserver(categoryData)
                Log.e(TAG, "onCreateViewHolder: checkbox clicked", )
            }
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

//        holder.checkBox.setOnCheckedChangeListener(null)
//        holder.checkBox.isChecked = getItem(position).isSelected
        holder.itemView.background =mContext.getDrawable(R.drawable.listview_item_border)
//        holder.itemView.background = when(getItem(position).isSelected){
//            true -> mContext.getDrawable(R.drawable.listview_item_border_purple)
//            false -> mContext.getDrawable(R.drawable.listview_item_border)
//        }
        //click row event: will change the checkbox and background
        holder.itemView.setOnClickListener {
           getItem(holder.adapterPosition).isSelected = !getItem(position).isSelected

            val changeCount =when(getItem(position).isSelected){
                true -> +1
                false -> -1
            }
            count+=changeCount
            if(count<=0 ){
                (mContext as FollowYourIndustryActivity).buttonSetEnable(false)
            }
            else
            {
                (mContext as FollowYourIndustryActivity).buttonSetEnable(true)
            }
            holder.itemView.background = when(getItem(position).isSelected){
                true -> mContext.getDrawable(R.drawable.listview_item_border_purple)
                false -> mContext.getDrawable(R.drawable.listview_item_border)
            }
            holder.checkBox.setChecked(getItem(position).isSelected)
            Log.e("abc","item: ${getItem(position).categoryInfo} isSelected: ${getItem(position).isSelected} Count = "+count)
        }

    }
    companion object{
        val TAG = "Following industry"
        var count = 0 // field variable
    }
}