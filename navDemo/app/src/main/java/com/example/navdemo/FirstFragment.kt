package com.example.navdemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navdemo.databinding.FragmentFristBinding
import kotlinx.android.synthetic.main.fragment_second.*
import kotlinx.android.synthetic.main.fragment_second.view.*


class FirstFragment : Fragment() {
    lateinit var binding: FragmentFristBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFristBinding.inflate(inflater, container, false)
        initView()

        return binding.root

    }
    private fun initView(){
//        binding.btnJump.setOnClickListener {
//            findNavController().navigate(R.id.second_frag)
//        }
//        binding.btnJump.setOnClickListener {
//            findNavController().navigate(R.id.second)
//        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Log.e("first frg", "onCreate: ${it.getString("string")}")
        }
    }


}