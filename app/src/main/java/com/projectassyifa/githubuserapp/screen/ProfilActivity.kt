package com.projectassyifa.githubuserapp.screen

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.projectassyifa.githubuserapp.R
import com.projectassyifa.githubuserapp.data.GithubViewModel
import com.projectassyifa.githubuserapp.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    private lateinit var githubVM : GithubViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME,username)
        githubVM = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)
        binding.apply {
            githubVM.userDetail.observe(this@ProfilActivity, {
                if (it!=null) {
                    nameTv.text = it.name
                    companyTv.text = it.company
                    locationTv.text = it.location
                    usernameTv.text = it.login
                    followerTv.text = it.followers.toString()
                    followingTv.text =it.following.toString()
                    Glide.with(this@ProfilActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .placeholder(R.drawable.user_profil)
                        .into(profilUser)
                }
            })
            githubVM.detailUser(username.toString())
            tabs.setSelectedTabIndicatorColor(Color.WHITE)
            tabs.tabTextColors = ContextCompat.getColorStateList(this@ProfilActivity, android.R.color.white)
            val followScreenAdapter = FollowScreenAdapter(this@ProfilActivity,bundle)
            viewPager.adapter = followScreenAdapter
            TabLayoutMediator(tabs,viewPager){tab,position ->
                tab.text = resources.getString(TAB_TITLES[position])
            }.attach()

        }

    }

    companion object {
        @StringRes
        private  val TAB_TITLES = intArrayOf(
            R.string.follower ,
            R.string.following
        )
        const val EXTRA_USERNAME = "userame"


    }
}