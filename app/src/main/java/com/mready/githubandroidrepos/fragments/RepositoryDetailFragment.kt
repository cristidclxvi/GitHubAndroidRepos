package com.mready.githubandroidrepos.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.mready.githubandroidrepos.DTO.Repository
import com.mready.githubandroidrepos.R
import com.mready.githubandroidrepos.viewmodels.RepositoryDetailViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.repository_detail_fragment.*


class RepositoryDetailFragment : Fragment() {

    companion object {
        const val REPO_OWNER_KEY = "OWNER_KEY"
        const val REPO_NAME_KEY = "NAME_KEY"
    }

    private lateinit var viewModel: RepositoryDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repository_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val owner = arguments?.getString(REPO_OWNER_KEY) ?: return
        val repo = arguments?.getString(REPO_NAME_KEY) ?: return

        viewModel = ViewModelProviders.of(this).get(RepositoryDetailViewModel::class.java)
        viewModel.getRepositoryLiveData(owner, repo).observe(this, Observer<Repository> {
            if(it != null) loading_indicator.visibility = View.GONE
            Picasso.get().load(it.owner?.avatar_url).into(owner_avatar)
            name_text.text = it.name
            owner_text.text = it.owner?.login
            description_text.text = it.description
            repo_link.text = it.html_url
            val stats = "Language: ${it.language}\n" +
                    "Watchers: ${it.watchers_count}\n" +
                    "Forks: ${it.forks_count}\n" +
                    "Open Issues: ${it.open_issues_count}"
            stats_text.text = stats
        })

        viewModel.getReadmeLiveData(owner, repo).observe(this, Observer<String> {
            readme_text.text = it
        })
    }

}
