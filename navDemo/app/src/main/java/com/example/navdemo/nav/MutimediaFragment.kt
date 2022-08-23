package com.example.navdemo.nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.navdemo.R
import com.example.navdemo.databinding.FragmentMutimediaBinding

class MutimediaFragment : Fragment() {
    lateinit var binding: FragmentMutimediaBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mutimedia, container, false)
    }

}