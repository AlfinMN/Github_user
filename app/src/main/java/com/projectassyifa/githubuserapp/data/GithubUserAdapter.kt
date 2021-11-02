package com.projectassyifa.githubuserapp.data

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.projectassyifa.githubuserapp.R
import com.projectassyifa.githubuserapp.screen.ProfilActivity

class GithubUserAdapter(val listUser : ArrayList<GithubUserModel> , var activity: Activity) : RecyclerView.Adapter<GithubUserAdapter.GithubVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubVH {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return GithubVH(view)
    }
    override fun onBindViewHolder(holder: GithubVH, position: Int) {
        val userPosition = listUser[position]
        holder.tvUsername.text = userPosition.username
        holder.tvName.text = userPosition.name
        Glide.with(holder.itemView.context)
            .load(userPosition.avatar)
            .apply(RequestOptions().override(50,50))
            .into(holder.avatar)
        holder.itemView.setOnClickListener{
            this.activity
           val toProfil = Intent(it.context,ProfilActivity::class.java)
            val user = GithubUserModel(
                userPosition.username,
                userPosition.name,
                userPosition.avatar,
                userPosition.follower,
                userPosition.following,
                userPosition.company,
                userPosition.location,
                userPosition.repository,
                userPosition.phone
            )
            toProfil.putExtra(ProfilActivity.EXTRA_USER,user)
            activity.startActivity(toProfil)
        }
        holder.phone.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:"+userPosition.phone)
            activity.startActivity(dialIntent)
        }
    }
    override fun getItemCount(): Int {
        return listUser.size
    }
    class GithubVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvUsername : TextView = itemView.findViewById(R.id.tv_username)
        var tvName : TextView = itemView.findViewById(R.id.tv_name)
        var avatar : ImageView = itemView.findViewById(R.id.avatar_user)
        var phone : ImageButton = itemView.findViewById(R.id.phone)
    }
}