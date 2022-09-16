package com.example.viwiki.domain.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viwiki.ViWikiApplication
import com.example.viwiki.databinding.FragmentSavedBinding

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
        // Create a listener to navigate to the page
        val listener = AdapterClickListener { pageTitle ->
            val action = SavedFragmentDirections.actionSavedFragmentToPageFragment()
            action.argArticleTitle = pageTitle
            findNavController().navigate(action)
        }
        // Instantiate adapter
        SavedAdapter(listener)
    }


    /**
     * Click listener to use in the adapter
     * @param callback function to execute on click
     */
    inner class AdapterClickListener(private val callback: (pageTitle: String) -> Unit) {
        /**
         * Calls the callback function
         * @param the page title
         */
        fun onClick(pageTitle: String) {
            return callback(pageTitle)
        }
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