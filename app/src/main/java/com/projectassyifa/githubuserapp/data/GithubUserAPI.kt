package com.projectassyifa.githubuserapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubUserAPI {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun searchUsers(@Query("q") q : String ) : Call<ResponseAPI>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun detailUser(@Path("username")username:String ) : Call<GithubUserDetailModel>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun followers(@Path("username")username: String) : Call<ArrayList<GithubUserModel>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun following(@Path("username")username: String) : Call<ArrayList<GithubUserModel>>
}

