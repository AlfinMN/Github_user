package com.projectassyifa.githubuserapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubViewModel : ViewModel(){
    val listUser = MutableLiveData<ArrayList<GithubUserModel>>()
    val userDetail = MutableLiveData<GithubUserDetailModel>()
    val listFollower = MutableLiveData<ArrayList<GithubUserModel>>()
    val listFollowing = MutableLiveData<ArrayList<GithubUserModel>>()
    
    fun searchUsers(q : String){
        GithubClient.instanceApi
            .searchUsers(q).enqueue(object : Callback<ResponseAPI> {
                override fun onResponse(call: Call<ResponseAPI>, response: Response<ResponseAPI>) {
                 if (response.isSuccessful){
                    val resvalue = response.body()?.items
                     listUser.value = resvalue
                 }
                }

                override fun onFailure(call: Call<ResponseAPI>, t: Throwable) {
                   t.printStackTrace()
                }
            })
    }
    fun dataSearch(): LiveData<ArrayList<GithubUserModel>>{
        return listUser
    }

    fun detailUser(username : String ){
        GithubClient.instanceApi
            .detailUser(username).enqueue(object : Callback<GithubUserDetailModel>{
                override fun onResponse(
                    call: Call<GithubUserDetailModel>,
                    response: Response<GithubUserDetailModel>
                ) {
                    if (response.isSuccessful){
                        userDetail.value = response.body()
                    }
                }

                override fun onFailure(call: Call<GithubUserDetailModel>, t: Throwable) {
                    t.printStackTrace()
                }

            })
        }

    fun followers(username: String){
        GithubClient.instanceApi
            .followers(username).enqueue(object : Callback<ArrayList<GithubUserModel>>{
                override fun onResponse(
                    call: Call<ArrayList<GithubUserModel>>,
                    response: Response<ArrayList<GithubUserModel>>
                ) {
                    if (response.isSuccessful){
                        listFollower.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUserModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun dataFollower():LiveData<ArrayList<GithubUserModel>>{
        return listFollower
    }

    fun following(username: String){
        GithubClient.instanceApi
            .following(username).enqueue(object : Callback<ArrayList<GithubUserModel>>{
                override fun onResponse(
                    call: Call<ArrayList<GithubUserModel>>,
                    response: Response<ArrayList<GithubUserModel>>
                ) {
                    if (response.isSuccessful){
                        listFollowing.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ArrayList<GithubUserModel>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
    }

    fun dataFollowing():LiveData<ArrayList<GithubUserModel>>{
        return listFollowing
    }
}