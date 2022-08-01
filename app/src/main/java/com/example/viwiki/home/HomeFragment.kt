package com.example.viwiki.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.load
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentHomeBinding
import com.example.viwiki.utils.Logger

/**
 * Home screen of the App. Shows the article of the day.
 */
class HomeFragment : Fragment() {
    val TAG = "HomeFragment"

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        // TODO try removing R.layout.fragment_home
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )

        // Allow for binding to observe LiveData
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        // TODO error message for blank response
        // TODO adapter for photo?

        viewModel.featuredArticleResponse.observe(viewLifecycleOwner, Observer {
                Logger.logInfo(TAG, "DisplayTitle" + it.tfa.displayTitle)
                // Pass data into binding variables
                binding.imageFeatured.load(it.tfa.thumbnail.source)
                // Refresh binding
                binding.executePendingBindings()
        })


        // Fetch data from API
        viewModel.fetchTodayFeaturedArticle()

        // Wire buttons
        binding.btnRefresh.setOnClickListener {
            viewModel.fetchTodayFeaturedArticle()
        }

        return binding.root
    }
}

