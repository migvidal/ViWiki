package com.example.viwiki.article_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentArticleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ArticleFragment : Fragment() {

    private var articleName: String? = null


    val viewModel: ArticleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentArticleBinding>(
            inflater, R.layout.fragment_article, container, false
        )

        // handle intents
        handleNameIntent()

        // observe
        viewModel.articleResponse.observe(viewLifecycleOwner, Observer {
            binding.apply {
                article = it.query.pages[0]
                executePendingBindings()
            }
        })
        viewModel.fetchArticleByTitle("Alberto Chicote")
        return binding.root
    }

    private fun handleNameIntent() {
    }

    companion object {
        val ARTICLE_NAME_CODE = "articleName"
    }
}