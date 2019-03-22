package com.mready.githubandroidrepos.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubRawContentInterface {

    @GET("/{owner}/{repo}/{branch}/README.md")
    fun getRawReadMe(
        @Path("owner") owner: String,
        @Path("repo") repo: String,
        @Path("branch") branch: String = "master"
    ): Call<String>
}