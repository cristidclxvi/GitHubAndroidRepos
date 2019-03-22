package com.mready.githubandroidrepos.DTO

import com.google.gson.annotations.SerializedName

open class SearchItem {
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("name")
    var name: String? = null
    @SerializedName("full_name")
    var full_name: String? = null
    @SerializedName("owner")
    var owner: Owner? = null
    @SerializedName("html_url")
    var html_url: String? = null
    @SerializedName("description")
    var description: String? = null
    @SerializedName("url")
    var url: String? = null
}



