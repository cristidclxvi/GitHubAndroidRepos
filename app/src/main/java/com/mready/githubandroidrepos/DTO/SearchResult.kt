package com.mready.githubandroidrepos.DTO

import com.google.gson.annotations.SerializedName

class SearchResult(
    @SerializedName("total_count")
    val total_count: Long,
    @SerializedName("incomplete_results")
    val incomplete_results: Boolean,
    @SerializedName("items")
    val items: List<SearchItem>
)