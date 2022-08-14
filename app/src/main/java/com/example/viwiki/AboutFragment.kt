package com.example.viwiki

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * The "about" screen
 */
class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val githubUri = Uri.Builder()
            .scheme("https")
            .authority("github.com")
            .appendPath("migvidal")
            .build()

        val websiteUri = Uri.Builder()
            .scheme("https")
            .authority("migvidal.github.io")
            .build()

        val intent = Intent(Intent.ACTION_VIEW, githubUri)
        startActivity(intent)

        return inflater.inflate(R.layout.fragment_about, container, false)
    }

 }