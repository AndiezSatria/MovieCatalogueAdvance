package com.andiez.moviecatalogueadvance.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.andiez.moviecatalogueadvance.MainActivity
import com.andiez.moviecatalogueadvance.R
import com.andiez.moviecatalogueadvance.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        (requireActivity() as MainActivity).supportActionBar?.setTitle(R.string.text_home)
    }
}