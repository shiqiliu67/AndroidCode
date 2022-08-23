package com.example.tagselectdemo

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.accenture.categorydemo.TagListDrawerAdapter
import com.example.tagselectdemo.databinding.LayoutBottomSheetTagListBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class TheBottomSheetTagListActivity : BottomSheetDialogFragment() {
    lateinit var binding: LayoutBottomSheetTagListBinding
    var bottomSheetBehavior: BottomSheetBehavior<View> = BottomSheetBehavior()
    lateinit var currentActivity: Any
    val viewmodel by activityViewModels<MainViewModel>()
   // lateinit var mAdapter: TagListAdapter
    lateinit var drawerAdapter: TagListDrawerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LayoutBottomSheetTagListBinding.inflate(inflater, container, false)
        currentActivity = requireActivity()
        setAdapter()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.let {
            val sheet = it as BottomSheetDialog
            sheet.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            sheet.behavior.skipCollapsed = true
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val d = super.onCreateDialog(savedInstanceState)
        d.setOnShowListener {
            bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.setBottomSheetCallback(object :
                BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when (newState) {
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                        }
                        BottomSheetBehavior.STATE_EXPANDED -> {
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                        }
                        BottomSheetBehavior.STATE_DRAGGING -> {
                        }
                        BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        }
                        BottomSheetBehavior.STATE_SETTLING -> {
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

            })
        }
        /*set color*/
//        if (currentActivity is MainActivity) {
//            (currentActivity as MainActivity).binding.toolbar.setBackgroundColor(
//                (currentActivity as MainActivity).getColor(
//                    R.color.dark_grey_specific
//                )
//            )
//            val window = (currentActivity as MainActivity).window
//            window.statusBarColor =
//                (currentActivity as MainActivity).getColor(R.color.dark_grey_specific)
//        }
        return d
    }

    private fun setAdapter() {
        //mAdapter = TagListAdapter()
        drawerAdapter = TagListDrawerAdapter {
            Log.e(TAG, "setAdapter: tag be clicked, tag is $it", )
            //open a activity here
        }
        binding.rvTagList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = drawerAdapter
            isNestedScrollingEnabled = false
        }
        Log.e(TAG, "setAdapter: ", )
        viewmodel.livedataTagList.observe(viewLifecycleOwner) {
            Log.e(TAG, "setAdapter: observe the value:$it", )
           // mAdapter.setData(it)
            drawerAdapter.submitList(it)
            Log.e(TAG, "setAdapter: tag list $it ")
        }

    }

    companion object {
        const val TAG = "TheBottomSheetTagListActivity"
    }
//    fun setTag(tagList:ArrayList<String>){
//        setAdapter()
//        mAdapter.setData(tagList)
//    }
}