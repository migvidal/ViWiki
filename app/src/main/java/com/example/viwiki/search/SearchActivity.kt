package com.example.viwiki.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viwiki.R
import com.example.viwiki.utils.Logger

class SearchActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        Logger.logInfo("Backcount", supportFragmentManager.backStackEntryCount.toString())
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
            return true
        }
        return super.onSupportNavigateUp()
    }

}
