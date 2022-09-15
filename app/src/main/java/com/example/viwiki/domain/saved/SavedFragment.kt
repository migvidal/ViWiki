package com.example.viwiki.domain.saved

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.viwiki.R
import com.example.viwiki.ViWikiApplication
import com.example.viwiki.databinding.FragmentSavedBinding
import com.example.viwiki.domain.page.PageFragmentArgs

class SavedFragment : Fragment() {

    companion object {
        fun newInstance() = SavedFragment()
    }

    private val viewModel: SavedViewModel by viewModels {
        SavedViewModelFactory(
            (activity?.application as ViWikiApplication).pageRepositoryImpl
        )
    }
    private val savedAdapter by lazy {
        val listener = View.OnClickListener {
            val action = SavedFragmentDirections.actionSavedFragmentToPageFragment()
            action.argArticleTitle = ""
            findNavController().navigate(action)
        }
        SavedAdapter(listener)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSavedBinding.inflate(inflater, container, false)
        binding.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.savedAdapter = savedAdapter
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Subscribe to the viewmodel
        viewModel.savedPages.observe(viewLifecycleOwner) {
            savedAdapter.submitList(it)
        }
    }

}