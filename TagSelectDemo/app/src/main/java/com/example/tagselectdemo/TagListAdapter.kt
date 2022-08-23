package com.example.tagselectdemo

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tagselectdemo.databinding.RowAdapterTagListBinding

class TagListAdapter() : RecyclerView.Adapter<TagListViewHolder>() {

    private var mList : ArrayList<String> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagListViewHolder {

        val view = RowAdapterTagListBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return TagListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TagListViewHolder, position: Int) {
        val article = mList[position]

        val ldr = holder.bind(article)

        return ldr
    }

    override fun getItemCount(): Int = mList.size


    fun setData(articleList : ArrayList<String>){
        mList = articleList
        notifyDataSetChanged()
        Log.e(TAG, "setData: articleList $articleList" )
    }
    companion object{
        const val  TAG ="TagListAdapter"
    }
}