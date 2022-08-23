package com.example.sharedviewmodelapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.sharedviewmodelapplication.databinding.FragmentUrlLinkBinding

class UrlLinkFragment : Fragment() {
    lateinit var binding: FragmentUrlLinkBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUrlLinkBinding.inflate(inflater,container,false)
        initView()
        clickEvent()
        return binding.root
    }
    private fun initView(){
        binding.textView.text = "facebook"
        binding.textView2.text = "google"
        binding.textView3.text = "https://amazon.com"
    }
    private fun clickEvent(){
        val viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        binding.textView.setOnClickListener {
            viewModel.selectedUrl.postValue("https://facebook.com")
        }
        binding.textView2.setOnClickListener {
            viewModel.selectedUrl.postValue("https://google.com")
        }
        binding.textView3.setOnClickListener {
            viewModel.selectedUrl.postValue(binding.textView3.text.toString())
        }
    }

}