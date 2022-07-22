package com.example.viwiki

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.viwiki.search.SearchActivity

class MainActivity : AppCompatActivity() {

    lateinit var mArticleName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Handle intent
        val articleName = intent.getStringExtra(ARTICLE_NAME)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)

        // Search elements
        val searchItem = menu.findItem(R.id.action_search) //Search menu item
        val searchView = searchItem.actionView as SearchView //The associated embedded SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        // The class that will perform the search for this activity
        val componentName = ComponentName(this, SearchActivity::class.java)
        // Set the searchable info
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(SEARCH_SERVICE))
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    companion object {
        val ARTICLE_NAME = "articleName"
    }
}