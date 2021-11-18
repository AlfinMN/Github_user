package com.projectassyifa.githubuserapp.data

data class GithubUserModel(
    val login : String ,
    val id : Int,
    val avatar_url : String,
)
data class GithubUserDetailModel(
    var name : String,
    var login : String ,
    var avatar_url: String,
    var company : String,
    var location : String,
    var bio : String,
    var followers : Int,
    var following :Int

)
data class ResponseAPI(
    val items : ArrayList<GithubUserModel>
)


