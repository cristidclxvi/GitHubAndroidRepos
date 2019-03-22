package com.mready.githubandroidrepos.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mready.githubandroidrepos.DTO.Repository
import com.mready.githubandroidrepos.retrofit.APIClients
import com.mready.githubandroidrepos.retrofit.GitHubInterface
import com.mready.githubandroidrepos.retrofit.GitHubRawContentInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class RepositoryDetailViewModel : ViewModel() {

    private lateinit var repositoryLiveData: MutableLiveData<Repository>
    private lateinit var readmeLiveData: MutableLiveData<String>

    private val gitHubInterface = APIClients.githubClient!!.create(GitHubInterface::class.java)
    private val gitHubRawContentInterface = APIClients.githubRawContentClient!!.create(GitHubRawContentInterface::class.java)

    fun getRepositoryLiveData(owner: String, repo: String): LiveData<Repository>{
        if(!::repositoryLiveData.isInitialized){
            repositoryLiveData = MutableLiveData()
            getRepository(owner, repo)
        }
        return repositoryLiveData
    }

    fun getReadmeLiveData(owner: String, repo: String): LiveData<String>{
        if(!::readmeLiveData.isInitialized){
            readmeLiveData = MutableLiveData()
            getReadme(owner, repo)
        }
        return readmeLiveData
    }

    private fun getRepository(owner: String, repo: String){
        gitHubInterface.getRepository(owner, repo)
            .enqueue(object : Callback<Repository> {
                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    Timber.e(t)
                }

                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    if(response.isSuccessful){
                        repositoryLiveData.value = response.body()
                    } else Timber.d("${response.code()} ${response.message()}")
                }
            })
    }

    private fun getReadme(owner: String, repo: String){
        gitHubRawContentInterface.getRawReadMe(owner, repo)
            .enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Timber.e(t)
                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.isSuccessful) {
                        readmeLiveData.value = response.body()
                    } else Timber.d("${response.code()} ${response.message()}")
                }
            })
    }

}
