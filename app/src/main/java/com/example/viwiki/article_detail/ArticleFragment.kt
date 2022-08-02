package com.example.viwiki.article_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.viwiki.R
import com.example.viwiki.databinding.FragmentArticleBinding

/**
 * A simple [Fragment] subclass.
 * Use the [ArticleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

// The fragment initialization parameters
private const val ARG_ARTICLE_NAME = ""

class ArticleFragment : Fragment() {

    /**
     * Navigation safeargs
     */
    private val navArgs: ArticleFragmentArgs by navArgs()

    /**
     * The name of the article to show
     */
    private var articleName: String? = null

    /**
     * View model for the data
     */
    private val viewModel: ArticleViewModel by viewModels()

    /**
     * Get the articleName passed by safe navArgs or by newInstance(arg)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get article name from newInstance args
        articleName = arguments?.getString(ARG_ARTICLE_NAME)

        // Get article name from the navigation safeargs
        navArgs.argArticleName.let {
            if (it.isNotBlank()) articleName = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentArticleBinding>(
            inflater, R.layout.fragment_article, container, false
        )

        // Observe viewModel
        viewModel.articleResponse.observe(viewLifecycleOwner, Observer {
            binding.apply {
                article = it.query.pages[0]
                executePendingBindings()
            }
        })

        // Trigger the API request
        if (articleName !== null) {
            viewModel.fetchArticleByTitle(articleName!!)
        }

        return binding.root
    }

    companion object {
        /**
         * Creates an instance of this ArticleFragment.
         * It's used to show the fragment in a different Activity.
         * @param articleTitle The exact title of the article
         */
        @JvmStatic
        fun newInstance(articleTitle: String) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ARTICLE_NAME, articleTitle)
                }
            }
    }
}