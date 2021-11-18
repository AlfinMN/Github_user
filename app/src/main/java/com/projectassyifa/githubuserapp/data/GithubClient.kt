package com.projectassyifa.githubuserapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubClient {
    private const val URL_API = "https://api.github.com/"

    val connection = Retrofit.Builder()
        .baseUrl(URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val instanceApi = connection.create(GithubUserAPI::class.java)
}