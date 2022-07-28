package com.example.viwiki.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater, R.layout.fragment_home, container, false
        )
        // Data binding
        viewModel.featuredArticleResponse.observe(viewLifecycleOwner, Observer {
            Logger.logInfo("The_info: ", it.tfa.title)
            binding.apply {
                featuredArticle = it
                imageView.load(it.tfa.thumbnail.source)
                executePendingBindings()
            }
        })
        viewModel.fetchTodayFeaturedArticle()

        // Button
        // ...

        return binding.root
    }
}