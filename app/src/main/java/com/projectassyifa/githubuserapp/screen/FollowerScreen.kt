package com.projectassyifa.githubuserapp.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectassyifa.githubuserapp.data.GithubUserAdapter
import com.projectassyifa.githubuserapp.data.GithubViewModel
import com.projectassyifa.githubuserapp.databinding.FragmentFollowerScreenBinding


class FollowerScreen : Fragment() {

    private var _binding: FragmentFollowerScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var githubViewModel: GithubViewModel
    private lateinit var githubUserAdapter: GithubUserAdapter
    private lateinit var username : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowerScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var argmnt = arguments
        username = argmnt?.getString(ProfilActivity.EXTRA_USERNAME).toString()

        githubUserAdapter = GithubUserAdapter()
        githubUserAdapter.notifyDataSetChanged()

        binding.apply {
            followerRv.setHasFixedSize(true)
            followerRv.layoutManager = LinearLayoutManager(activity)
            followerRv.adapter = githubUserAdapter
        }

        showLoad(true)
        githubViewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)
        githubViewModel.followers(username)
        githubViewModel.dataFollower().observe(viewLifecycleOwner,{
            if (it!=null){
                githubUserAdapter.setList(it)
                showLoad(false)
            }
        })
    }
    private fun showLoad(state : Boolean){
        if (state){
            binding.loadUser.visibility = View.VISIBLE
        }else{
            binding.loadUser.visibility = View.GONE
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}