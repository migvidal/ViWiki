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

// the fragment initialization parameters
private const val ARG_ARTICLE_NAME = ""

class ArticleFragment : Fragment() {
    private var articleTitle: String? = null

    val viewModel: ArticleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articleTitle = arguments?.getString(ARG_ARTICLE_NAME)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentArticleBinding>(
            inflater, R.layout.fragment_article, container, false
        )

        // observe
        viewModel.articleResponse.observe(viewLifecycleOwner, Observer {
            binding.apply {
                article = it.query.pages[0]
                executePendingBindings()
            }
        })
        if (articleTitle !== null) {
            viewModel.fetchArticleByTitle(articleTitle!!)
        }
        return binding.root
    }


    companion object {
        @JvmStatic
                /**
                 * Creates an instance of this ArticleFragment
                 * @param articleTitle The exact title of the article
                 */
        fun newInstance(articleTitle: String) =
            ArticleFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ARTICLE_NAME, articleTitle)
                }
            }
    }
}