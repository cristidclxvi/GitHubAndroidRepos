package com.mready.githubandroidrepos.DTO

import com.google.gson.annotations.SerializedName

class Owner(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatar_url: String?
)