package com.projectassyifa.githubuserapp.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class FollowScreenAdapter (activity : AppCompatActivity,username : Bundle): FragmentStateAdapter(activity){

    private var fBundle : Bundle

    init {
        fBundle =username
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowerScreen()
            1 -> fragment = FollowingScreen()
        }
        fragment?.arguments = this.fBundle
        return fragment as Fragment
    }
}