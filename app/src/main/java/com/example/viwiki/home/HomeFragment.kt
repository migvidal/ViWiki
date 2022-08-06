package com.example.viwiki.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentHomeBinding
import timber.log.Timber

/**
 * Home screen of the App. Shows the article of the day.
 */
class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("Fragment onCreate Called")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.i("Fragment onCreateView Called")
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        // TODO error message for blank response

        // TODO fragment for error / status screen?
        // Wire buttons:
        // - refresh button
        binding.btnRefresh.setOnClickListener {
            viewModel.fetchTodayFeaturedArticle()
        }

        // - gotoFullArticle button
        binding.btnGotoFullArticle.setOnClickListener {
            val articleName = viewModel.featuredArticleResponse.value?.tfa?.title
            if (articleName !== null) {
                val action = HomeFragmentDirections.actionHomeFragmentToArticleFragment()
                action.argArticleName = articleName
                findNavController().navigate(action)
            }
        }
        return binding.root
    }

}



