package com.mready.githubandroidrepos.data

import androidx.paging.PageKeyedDataSource
import com.mready.githubandroidrepos.DTO.SearchItem
import com.mready.githubandroidrepos.DTO.SearchResult
import com.mready.githubandroidrepos.retrofit.APIClients
import com.mready.githubandroidrepos.retrofit.GitHubInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class SearchItemsDataSource: PageKeyedDataSource<Int, SearchItem>() {

    private val gitHubInterface = APIClients.githubClient!!.create(GitHubInterface::class.java)

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, SearchItem>) {
        gitHubInterface.searchRepositories(page = 1, per_page = params.requestedLoadSize)
            .enqueue(object : Callback<SearchResult>{
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Timber.e(t)
                }

                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    if(response.isSuccessful && response.body() != null){
                        val searchList = response.body()!!.items
                        callback.onResult(
                            searchList,
                            0,
                            searchList.size,
                            null,
                            2
                        )
                    } else Timber.d(response.message())
                }

            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SearchItem>) {
        val currentPage = params.key

        gitHubInterface.searchRepositories(page = currentPage, per_page = params.requestedLoadSize)
            .enqueue(object : Callback<SearchResult>{
                override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                    Timber.e(t)
                }

                override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                    if(response.isSuccessful && response.body() != null){
                        val searchList = response.body()!!.items
                        callback.onResult(
                            searchList,
                            currentPage + 1
                        )
                    } else Timber.d(response.message())
                }

            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SearchItem>){}
}