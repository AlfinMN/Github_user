package com.projectassyifa.githubuserapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.projectassyifa.githubuserapp.R
import com.projectassyifa.githubuserapp.data.GithubUserAdapter
import com.projectassyifa.githubuserapp.data.GithubUserModel
import com.projectassyifa.githubuserapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val list = ArrayList<GithubUserModel>()
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.userRv.setHasFixedSize(true)
        list.addAll(listUser)
        showList()
    }
    private val listUser:ArrayList<GithubUserModel>
    get() {
        val name = resources.getStringArray(R.array.github_name)
        val username = resources.getStringArray(R.array.github_username)
        val avatar = resources.obtainTypedArray(R.array.github_avatar)
        val follower = resources.getStringArray(R.array.github_follower)
        val following = resources.getStringArray(R.array.github_following)
        val company = resources.getStringArray(R.array.github_company)
        val location = resources.getStringArray(R.array.github_location)
        val repo = resources.getStringArray(R.array.github_repo)
        val phone = resources.getStringArray(R.array.github_phone)
        val listGithubUser = ArrayList<GithubUserModel>()
        for (position in name.indices ) {
            val user = GithubUserModel(username[position],name[position],avatar.getResourceId(position,-1),follower[position],following[position],company[position],location[position],repo[position],phone[position])
            listGithubUser.add(user)
        }
        return listGithubUser
    }
    private fun showList() {
        binding.userRv.layoutManager = LinearLayoutManager(this)
        val githubUserAdapter = GithubUserAdapter(list,this)
        binding.userRv.adapter = githubUserAdapter
    }
}