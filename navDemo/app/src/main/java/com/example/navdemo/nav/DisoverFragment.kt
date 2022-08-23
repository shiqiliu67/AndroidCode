package com.example.navdemo.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navdemo.R
import com.example.navdemo.databinding.FragmentDisoverBinding

class DisoverFragment : Fragment() {
    lateinit var binding:FragmentDisoverBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDisoverBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }
    private fun initView(){
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.testFragment)
        }
    }
}