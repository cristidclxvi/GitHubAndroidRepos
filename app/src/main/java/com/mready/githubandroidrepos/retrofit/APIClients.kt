package com.mready.githubandroidrepos.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object APIClients {

    var githubClient: Retrofit? = null
    private set
    get() {
        if (field == null)
            field = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return field
    }

    var githubRawContentClient: Retrofit? = null
        private set
        get() {
            if (field == null)
                field = Retrofit.Builder()
                    .baseUrl("https://raw.githubusercontent.com")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            return field
        }
}