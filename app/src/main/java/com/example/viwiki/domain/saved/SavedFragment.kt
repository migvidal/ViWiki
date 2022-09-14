package com.example.viwiki.domain.saved

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFragment()
    }

    private val viewModel: SavedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.savedAdapter = SavedAdapter()
        return binding.root
    }

}