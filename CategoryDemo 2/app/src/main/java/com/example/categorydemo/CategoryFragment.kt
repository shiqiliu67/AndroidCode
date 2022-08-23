package com.example.categorydemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.categorydemo.databinding.FragmentCategoryBinding
import com.example.categorydemo.adapter.FollowingCategoryAdapter
import com.example.categorydemo.adapter.UnfollowCategoryAdapter
import com.example.categorydemo.data.CategoryData
import com.example.thought_leadership.data.room_db.Category
import com.example.thought_leadership.data.room_db.User
import com.example.thought_leadership.data.room_db.UsersDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {
    lateinit var viewModel : CategoryViewModel
    lateinit var followingAdapter: FollowingCategoryAdapter
    lateinit var unfollowadapter: UnfollowCategoryAdapter
    lateinit var binding: FragmentCategoryBinding
  //  lateinit var dbUser: UsersDataBase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    //    dbUser = UsersDataBase.getInstance(requireContext())
        viewModel = CategoryViewModel(requireContext())
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        initView()
        //setData()
        return binding.root
    }

    private fun initView() {
        //follow adapter
        followingAdapter = FollowingCategoryAdapter({
            //click intent to detailtag activity
            Log.e(TAG, "followingAdapter successful: ")
        }) {
            viewModel.editCategoryData(Category(it.categoryInfo!!,false,viewModel.user))

        }
        //unfollow adapter
        unfollowadapter = UnfollowCategoryAdapter({
            Log.e(TAG, "unfollow followingAdapter successful: ")
        }) {
            viewModel.editCategoryData(Category(it.categoryInfo!!,true,viewModel.user))

        }
        //following rv
        binding.rvFollowing.apply {
            isNestedScrollingEnabled = false
            adapter = followingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        //unfollowing rv
        binding.rvUnfollowing.apply {
            isNestedScrollingEnabled = false
            adapter = unfollowadapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        setData()
        viewModel.getAllCategoryData().observe(viewLifecycleOwner){
            if (it.isEmpty()) {
                Log.e(TAG, "initView: list is empty")

            }
            val unfollowList = ArrayList<CategoryData>()
            val followList = ArrayList<CategoryData>()

            for (item in it) {
                when (item.isSelected) {
                    true -> followList.add(
                        CategoryData(
                            categoryInfo = item.category,
                            isSelected = item.isSelected
                        )
                    )//CategoryData(item.category,item.isSelected)
                    else -> unfollowList.add(
                        CategoryData(
                            categoryInfo = item.category,
                            isSelected = item.isSelected
                        )
                    )
                }
            }
            if (followList.isEmpty()) {
                Log.e(TAG, "initView: followlist is empty")
                binding.followHeader.isVisible = false
            } else {
                binding.followHeader.isVisible = true

            }

            followingAdapter.submitList(followList)
            unfollowadapter.submitList(unfollowList)
        }

    }

    private fun setData() {
        var mList = viewModel.setCategoryInfo()
        lifecycleScope.launch(Dispatchers.IO) {
            // dbUser.userDao.deleteAllCateGoryData()
           for (item in mList)
                if (!viewModel.existsCategory(item.categoryInfo,viewModel.user)) {
                    viewModel.insertCategoryData(
                        Category( item.categoryInfo!!,
                            item.isSelected,
                            viewModel.user)
                    )
               }
        }


    }

    companion object {
        private val TAG = "CategoryFragment"

    }
}