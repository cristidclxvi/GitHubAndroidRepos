package com.mready.githubandroidrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.mready.githubandroidrepos.DTO.SearchItem
import com.mready.githubandroidrepos.data.SearchItemsDataSource
import com.mready.githubandroidrepos.utils.MainThreadExecutor

class RepositoriesViewModel : ViewModel() {

    private lateinit var searchItems: MutableLiveData<PagedList<SearchItem>>

    fun getSearchItemsLiveData(): LiveData<PagedList<SearchItem>> {
        if(!::searchItems.isInitialized){
            searchItems = MutableLiveData()
            getPagedList()
        }
        return searchItems
    }

    private fun getPagedList(){
        val executor = MainThreadExecutor()
        val pageSize = 50
        val dataSource = SearchItemsDataSource()
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize)
            .setEnablePlaceholders(true)
            .build()
        searchItems.value = PagedList.Builder(dataSource, config)
            .setFetchExecutor(executor)
            .setNotifyExecutor(executor)
            .build()
    }
}
