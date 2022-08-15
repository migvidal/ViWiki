package com.example.viwiki

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.viwiki.databinding.FragmentAboutBinding


/**
 * The "about" screen
 */
class AboutFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout with data binding
        val binding = FragmentAboutBinding.inflate(inflater, container, false)

        // Builder for contact URIs
        fun uriBuilder() = Uri.Builder()
            .scheme(getString(R.string.web_url_scheme))

        // Listener for website link button
        binding.btnLinkGithub.setOnClickListener {
            val githubUri = uriBuilder()
                .authority(getString(R.string.github_authority))
                .appendPath(getString(R.string.personal_github_path))
                .build()
            // Open the URI
            startActivity(Intent(Intent.ACTION_VIEW, githubUri))
        }
        // Listener for Github link button
        binding.btnLinkWebsite.setOnClickListener {
            val websiteUri = uriBuilder()
                .authority(getString(R.string.personal_website_authority))
                .build()
            // Open the URI
            startActivity(Intent(Intent.ACTION_VIEW, websiteUri))
        }

        // Return the view
        return binding.root
    }

}