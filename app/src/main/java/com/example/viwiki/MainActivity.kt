package com.example.viwiki

import android.app.SearchManager
import android.content.ComponentName
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.viwiki.databinding.ActivityMainBinding
import com.example.viwiki.search.SearchActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        // Set 'up' button
        val navController = this.findNavController(R.id.search_nav_host)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    override fun onStart() {
        super.onStart()
        // Handle intent
        if (intent.hasExtra(ARTICLE_TITLE_EXTRA_KEY)) {
            // Get intent
            val searchedArticleName = intent.getStringExtra(ARTICLE_TITLE_EXTRA_KEY)

            // Clear intent
            intent.removeExtra(ARTICLE_TITLE_EXTRA_KEY)

            // Put article name into a bundle
            val bundle = Bundle()
            bundle.putString("arg_article_title", searchedArticleName)

            // Navigate to fragment using nav controller and the Bundle
            findNavController(R.id.search_nav_host).navigate(R.id.articleFragment, bundle)

        }
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

    // Set 'up' button for older devices
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.search_nav_host)
        return navController.navigateUp()
    }

    companion object {
        /**
         * The key for the extra `articleTitle` in the received intent
         */
        val ARTICLE_TITLE_EXTRA_KEY = "articleTitle"
    }


}