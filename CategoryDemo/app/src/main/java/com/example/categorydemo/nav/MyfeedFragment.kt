package com.example.categorydemo.nav

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.categorydemo.MainActivity
import com.example.categorydemo.R
import com.example.categorydemo.databinding.FragmentMyfeedBinding

class MyfeedFragment : Fragment() {
    lateinit var binding: FragmentMyfeedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyfeedBinding.inflate(inflater, container, false)
        //event
        binding.btnJump.setOnClickListener {
            var intent = Intent(requireContext(),MainActivity::class.java)
            intent.putExtra("nav","topic")
            startActivity(intent)
        }
        return binding.root
    }

}