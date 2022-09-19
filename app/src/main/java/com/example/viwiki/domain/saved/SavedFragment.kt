package com.example.viwiki.domain.saved

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viwiki.R
import com.example.viwiki.ViWikiApplication
import com.example.viwiki.databinding.FragmentSavedBinding
import com.example.viwiki.domain.BaseListAdapter
import com.example.viwiki.domain.page.Page

class SavedFragment : Fragment() {










    /**
     * Adapter for the recycler view
     */
    private lateinit var listAdapter: BaseListAdapter<Page>

    /**
     * Saved pages ViewModel
     */
    private val viewModel: SavedViewModel by viewModels {
        SavedViewModelFactory(
            (activity?.application as ViWikiApplication).pageRepositoryImpl
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // instantiate adapter
        listAdapter = BaseListAdapter(
            R.layout.fragment_saved, ListItemClickListener { pageTitle ->
                val action = SavedFragmentDirections.actionSavedFragmentToPageFragment()
                action.argArticleTitle = pageTitle
                findNavController().navigate(action)
            })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // observe the viewModel
        viewModel.savedPages.observe(viewLifecycleOwner) {
            listAdapter.submitList(it)
        }

        // data binding
        val binding = FragmentSavedBinding.inflate(inflater, container, false)
        binding.let {
            it.lifecycleOwner = viewLifecycleOwner
            it.listAdapter = listAdapter
            it.btnRefresh.setOnClickListener {
                viewModel.loadSavedPages()
            }
        }
        // return the view
        return binding.root
    }
}