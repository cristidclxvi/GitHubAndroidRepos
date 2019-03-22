package com.mready.githubandroidrepos.retrofit

import com.mready.githubandroidrepos.DTO.Repository
import com.mready.githubandroidrepos.DTO.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubInterface {

    @Headers(
        "Accept: application/vnd.github.mercy-preview+json",
        "User-Agent: GitHubAndroidRepos"
    )
    @GET("/search/repositories")
    fun searchRepositories(
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 30,
        @Query("q") q: String = "topic:android",
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): Call<SearchResult>

    @Headers(
        "Accept: application/vnd.github.mercy-preview+json",
        "User-Agent: GitHubAndroidRepos"
    )
    @GET("/repos/{owner}/{repo}")
    fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<Repository>
}