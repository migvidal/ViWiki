package com.example.viwiki.domain.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viwiki.R

/**
 * Search results activity
 */
class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        // Enable up button in action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
