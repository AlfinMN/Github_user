package com.projectassyifa.githubuserapp.data


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.githubuserapp.R
import com.projectassyifa.githubuserapp.databinding.ItemUserBinding
import com.projectassyifa.githubuserapp.screen.ProfilActivity

class GithubUserAdapter : RecyclerView.Adapter<GithubUserAdapter.GithubVH>() {
    private val list = ArrayList<GithubUserModel>()
    fun setList(dataUsers: ArrayList<GithubUserModel>){
        list.clear()
        list.addAll(dataUsers)
        notifyDataSetChanged()
    }
    inner class GithubVH (val binding : ItemUserBinding) : RecyclerView.ViewHolder(binding.root){
        fun data(dataUser : GithubUserModel){
           binding.apply {
               Glide.with(itemView)
                   .load(dataUser.avatar_url)
                   .transition(DrawableTransitionOptions.withCrossFade())
                   .centerCrop()
                   .apply(RequestOptions().override(50,50))
                   .placeholder(R.drawable.ic_person)
                   .into(avatarUser)
                tvUsername.text = dataUser.login
                tvId.text = dataUser.id.toString()
               itemView.setOnClickListener{
                  val toProfil = Intent(it.context,ProfilActivity::class.java)
                   toProfil.putExtra(ProfilActivity.EXTRA_USERNAME,dataUser.login)
                   it.context.startActivity(toProfil)
               }
           }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubVH {
       val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return GithubVH((view))
    }

    override fun onBindViewHolder(holder: GithubVH, position: Int) {
        holder.data(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }


}