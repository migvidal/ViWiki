package com.example.viwiki.search

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.viwiki.R

class SearchActivity : AppCompatActivity() {

    val viewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        var testTextView = findViewById<TextView>(R.id.tv_test)

        // Verify intent
        if (intent.action == Intent.ACTION_SEARCH) {
            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                viewModel.searchResponse.observe(this, Observer {
                    testTextView.text = it.query?.search?.get(0)?.title.toString()
                })
                viewModel.searchArticles(query)
                // TODO recyclerview + viewbinding
            }
        }
    }

}