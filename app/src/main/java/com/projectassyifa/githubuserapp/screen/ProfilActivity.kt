package com.projectassyifa.githubuserapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.githubuserapp.R
import com.projectassyifa.githubuserapp.data.GithubUserModel
import com.projectassyifa.githubuserapp.databinding.ActivityHomeBinding
import com.projectassyifa.githubuserapp.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dataUser = intent.getParcelableExtra<GithubUserModel>(EXTRA_USER) as GithubUserModel

        binding.usernameTv.text = dataUser.username
        binding.nameTv.text = dataUser.name
        binding.repoTv.text = dataUser.repository
        Glide.with(this)
            .load(dataUser.avatar)
            .apply(RequestOptions().override(50,50))
            .into(binding.profilUser)
        binding.followerTv.text = dataUser.follower
        binding.followingTv.text = dataUser.following
        binding.companyTv.text = dataUser.company
        binding.locationTv.text = dataUser.location
    }
    companion object {
        const val EXTRA_USER = "user"
    }
}