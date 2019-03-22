package com.mready.githubandroidrepos.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.mready.githubandroidrepos.DTO.SearchItem
import com.mready.githubandroidrepos.R
import com.mready.githubandroidrepos.viewmodels.RepositoriesViewModel
import com.mready.githubandroidrepos.adapters.SearchItemsAdapter
import com.mready.githubandroidrepos.utils.OnRepositoryClickListener
import kotlinx.android.synthetic.main.repositories_fragment.*


class RepositoriesFragment : Fragment(), OnRepositoryClickListener {

    private lateinit var viewModel: RepositoriesViewModel
    private lateinit var adapter: SearchItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.repositories_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = SearchItemsAdapter(this)
        repos_recyclerview.adapter = adapter
        repos_recyclerview.addOnChildAttachStateChangeListener(object : RecyclerView.OnChildAttachStateChangeListener{
            override fun onChildViewDetachedFromWindow(view: View){}
            override fun onChildViewAttachedToWindow(view: View) {
                loading_indicator.visibility = View.GONE
                repos_recyclerview.clearOnChildAttachStateChangeListeners()
            }
        })

        viewModel = ViewModelProviders.of(this).get(RepositoriesViewModel::class.java)
        viewModel.getSearchItemsLiveData().observe(this, Observer<PagedList<SearchItem>> {
            adapter.submitList(it)
        })
    }

    override fun onRepositoryClick(owner: String, repo: String) = fragmentRepoDetails(owner, repo)

    private fun fragmentRepoDetails(owner: String, repo: String){
        val fragment = RepositoryDetailFragment()
        val args = Bundle()
        args.putString(RepositoryDetailFragment.REPO_OWNER_KEY, owner)
        args.putString(RepositoryDetailFragment.REPO_NAME_KEY, repo)
        fragment.arguments = args
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragment_container, fragment)
            ?.addToBackStack(null)
            ?.commit()
    }

}
