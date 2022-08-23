package com.example.categorydemo.nav

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.categorydemo.adapter.UnfollowCategoryTopicAdapter
import com.example.categorydemo.data.CategoryData
import com.example.thought_leadership.data.room_db.Category
import com.example.thought_leadership.data.room_db.UsersDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {
    lateinit var viewModel : CategoryViewModel
    lateinit var followingAdapter: FollowingCategoryAdapter
    lateinit var unfollowadapter: UnfollowCategoryAdapter
    lateinit var unfollowTopicAdapter: UnfollowCategoryTopicAdapter
    lateinit var binding: FragmentCategoryBinding
    lateinit var sharedPreferences: SharedPreferences
    var userId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate: ", )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        viewModel = CategoryViewModel(requireActivity())
        setUpView()
        initView()
        Log.e(TAG, "onCreateView: ", )
        return binding.root

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ", )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e(TAG, "onDestroyView: ", )
    }
    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: ")
        setData()
    }

    override fun onPause() {
        super.onPause()
        //  onDestroy()
        Log.e(TAG, "onPause: ", )
    }


    private fun setUpView() {
        //if exist user
        getCurrentUserId()
//        if (!userId.equals("test_user_id")){
//            findNavController().navigate(R.id.topicFragment)
//        }


//        if (userId.equals("test_user_id")) {
//            //current no user
//            //     binding.layoutNoUser.root.isVisible = true
//            binding.layoutCategory.isVisible = false
//        } else {
//            //     binding.layoutNoUser.root.isVisible = false
//            binding.layoutCategory.isVisible = true
//        }
//        binding.layoutNoUser.apply {
//            buttonCreateAccount.setOnClickListener {
//                val intent = Intent(requireContext(), B2CActivity::class.java)
//                intent.putExtra("b2c", 2)
//                startActivity(intent)
//            }
//            textViewGlobalFeed.setOnClickListener {
//                val intent = Intent(requireContext(), MainActivity::class.java)
//                intent.putExtra("navigate", 2)
//                startActivity(intent)
//            }
//        }
    }

    private fun getCurrentUserId() {
        sharedPreferences =
            requireContext().getSharedPreferences("current_user", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("user_id", "test_user_id")

    }

    private fun initView() {
        binding.followHeader.isVisible = false
        followingAdapter = FollowingCategoryAdapter({
            //click intent to detailtag activity
//            val intent = Intent(requireContext(), SpecificCategoryActivity::class.java)
//            intent.putExtra("tag", it)
//            startActivity(intent)
        }) {
            viewModel.editCategoryData(
                Category(
                    category = it.categoryInfo,
                    isSelected = false,
                    tag = it.tag,
                    userID = viewModel.user
                )
            )
        }
        unfollowadapter = UnfollowCategoryAdapter({
//            val intent = Intent(requireContext(), SpecificCategoryActivity::class.java)
//            intent.putExtra("tag", it)
//            startActivity(intent)
        }) {
            viewModel.editCategoryData(
                Category(
                    category = it.categoryInfo,
                    isSelected = true,
                    tag = it.tag,
                    userID = viewModel.user
                )
            )
//            setData()
        }
        unfollowTopicAdapter = UnfollowCategoryTopicAdapter({
//            val intent = Intent(requireContext(), SpecificCategoryActivity::class.java)
//            intent.putExtra("tag", it)
//            startActivity(intent)
        }) {
            viewModel.editCategoryData(
                Category(
                    category = it.categoryInfo,
                    isSelected = true,
                    tag = it.tag,
                    userID = viewModel.user
                )
            )
            //test
//            setData()
            Log.e(TAG, "initView: followed ")
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
        //unfollowing rv topic
        binding.rvUnfollowingTopic.apply {
            isNestedScrollingEnabled = false
            adapter = unfollowTopicAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        setData()
        //   setUpCategoryObserve()

    }

    private fun setUpCategoryObserve() {
        viewModel.getAllCategoryData(viewModel.user).observe(viewLifecycleOwner) {
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
                            isSelected = item.isSelected,
                            tag = item.tag
                        )
                    )//CategoryData(item.category,item.isSelected)
                    else -> unfollowList.add(
                        CategoryData(
                            categoryInfo = item.category,
                            isSelected = item.isSelected,
                            tag = item.tag
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
            //check tag
            val industryList = ArrayList<CategoryData>()
            val topicList = ArrayList<CategoryData>()
            for (item in unfollowList) {
                when (item.tag.equals("topic")) {
                    true -> {//put it in topic
                        topicList.add(item)
                    }
                    false -> {//put it in industry
                        industryList.add(item)
                    }
                }
            }
            unfollowadapter.submitList(industryList)
            unfollowTopicAdapter.submitList(topicList)
            Log.e(
                TAG,
                "setUpCategoryObserve: completed observe\nfollowed topic:${followList.size}, unfollow topic:${unfollowList.size}",
            )
        }
    }

    private fun setData() {
        val mList = viewModel.setCategoryIndustryInfo() + viewModel.setCategoryTopicInfo()
        lifecycleScope.launch(Dispatchers.IO) {
            for (item in mList)
                if (!viewModel.existsCategory(item.categoryInfo, viewModel.user)) {
                    viewModel.insertCategoryData(
                        Category(
                            category = item.categoryInfo,
                            isSelected = item.isSelected,
                            tag = item.tag,
                            userID = viewModel.user
                        )
                    )
                }
        }
        Log.e(TAG, "setData: ", )
        setUpCategoryObserve()

    }

    companion object {
        private val TAG = "CategoryFragment"
        @JvmStatic
        fun newInstance(): CategoryFragment = CategoryFragment()
    }

}