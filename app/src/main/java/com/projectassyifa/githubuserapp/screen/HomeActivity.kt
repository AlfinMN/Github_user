package com.projectassyifa.githubuserapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projectassyifa.githubuserapp.data.GithubUserAdapter
import com.projectassyifa.githubuserapp.data.GithubViewModel
import com.projectassyifa.githubuserapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var githubVM : GithubViewModel
    private lateinit var githubUserAdapter: GithubUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        githubUserAdapter = GithubUserAdapter()
        githubUserAdapter.notifyDataSetChanged()
        githubVM = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(GithubViewModel::class.java)
        binding.apply {
            userRv.setHasFixedSize(true)
            userRv.layoutManager = LinearLayoutManager(this@HomeActivity)
            userRv.adapter = githubUserAdapter

            etSearch.setOnKeyListener { view, i, keyEvent ->
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER ){
                    searching()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
            btnSearch.setOnClickListener{
                searching()
            }
            githubVM.dataSearch().observe(this@HomeActivity,{
                if (it != null){
                    githubUserAdapter.setList(it)
                    showLoad(false)
                }
            })

        }
    }
            private fun searching(){
                binding.apply {
                    val q = etSearch.text.toString()
                    if (q.isEmpty()) return
                    showLoad(true)
                    githubVM.searchUsers(q)
                }
            }
            private fun showLoad(state : Boolean){
                if (state){
                    binding.loadUser.visibility = View.VISIBLE
                }else{
                    binding.loadUser.visibility = View.GONE
                }
              }
}