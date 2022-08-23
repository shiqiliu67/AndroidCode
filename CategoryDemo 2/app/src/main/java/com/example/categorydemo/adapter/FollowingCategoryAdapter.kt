package com.example.categorydemo.adapter

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.categorydemo.data.CategoryData
import com.example.categorydemo.databinding.RowFollowCategoryAdapterBinding
import kotlin.collections.ArrayList

class FollowingCategoryAdapter(private val rootClick: (CategoryData) -> Unit, private val checkedObserver: (CategoryData) ->Unit) :
    androidx.recyclerview.widget.ListAdapter<CategoryData, FollowingCategoryAdapter.ViewHolder>(object :
        DiffUtil.ItemCallback<CategoryData>() {
        override fun areItemsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: CategoryData, newItem: CategoryData): Boolean =
            oldItem == newItem

    }) {

    inner class ViewHolder(var binding: RowFollowCategoryAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(discoverItem: CategoryData) {
            discoverItem.let {
                binding.categoriesText.text = it.categoryInfo //it.getCategoryInfo()
                //divide two list
//                if (binding.categoriesCheckbox.isChecked) {
//                    it.isSelected = true
//                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        RowFollowCategoryAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
        .apply {
            binding.apply {
                root.setOnClickListener {
                    rootClick(getItem(adapterPosition))
                    Log.e(TAG, "onCreateViewHolder: item click: ${getItem(adapterPosition)} ", )
                }

                categoriesCheckbox.setOnClickListener {
//                        compoundButton, b ->
//                    categoriesCheckbox.isChecked = false
//                    val categoryData = getItem(adapterPosition)
//                    if (b){
//                        delete(adapterPosition)
//                    }
//                    categoryData.isSelected = b
//                    checkedObserver(categoryData)
                    val categoryData = getItem(adapterPosition)
                    delete(adapterPosition)
                    categoryData.isSelected = false
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
//        var list = currentList
//        list.removeAt(item)
//        submitList(list)
        var newList = ArrayList<CategoryData>()
        newList.clear()
        newList.addAll(currentList)
        newList.removeAt(item)
        submitList(newList)
    }
companion object{
    private val TAG = "CategoryAdapter"
}

}
//class CategoryAdapter ():RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){
//    inner class ViewHolder(val binding: RowCategoryAdapterBinding):RecyclerView.ViewHolder(binding.root){
//        val checkBox: CheckBox = binding.categoriesCheckbox
//        val text: TextView = binding.categoriesText
//        fun bindItems(aMSetting: CategoryData) {
//            text.setText(aMSetting.getCategoryInfo())
//        }
//    }
//    private var mList : ArrayList<CategoryData> = ArrayList()
//    private var followingList :ArrayList<CategoryData> = ArrayList()
//    private var selectedPosition = -1
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = RowCategoryAdapterBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return ViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val category = mList[position]
//        return holder.bindItems(category)
//
//    }
//
//    override fun getItemCount(): Int = mList.size
//
//    fun setData(categoryList : ArrayList<CategoryData>){
//        mList = categoryList
//        notifyDataSetChanged()
//    }
//
//}
