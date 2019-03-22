package com.mready.githubandroidrepos.DTO

import com.google.gson.annotations.SerializedName

class Repository(
    @SerializedName("watchers_count")
    val watchers_count: Long,
    @SerializedName("language")
    val language: String,
    @SerializedName("forks_count")
    val forks_count: Long,
    @SerializedName("open_issues_count")
    val open_issues_count: Long
): SearchItem()