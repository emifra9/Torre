package com.emifra9.torre.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emifra9.torre.R
import com.emifra9.torre.data.User
import com.emifra9.torre.databinding.ItemFragmentBinding
import com.emifra9.torre.utils.toCamelCase

class UserAdapter : RecyclerView.Adapter<UserAdapter.MainViewHolder>() {

    var users = mutableListOf<User>()

    private lateinit var onClickUser: OnUserClicked


    interface OnUserClicked {
        fun onUserClick(user: User)
    }
    fun setOnClickUser(onClick: OnUserClicked) {
        this.onClickUser = onClick
    }

    fun setUsersList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemFragmentBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users[position]
        holder.binding.name.text = user.name.toCamelCase()
        Glide.with(holder.itemView.context).load(user.picture).placeholder(R.drawable.torre).circleCrop().into(holder.binding.imgProfile)
        holder.binding.main.setOnClickListener { onClickUser.onUserClick( user) }

    }

    override fun getItemCount(): Int {
        return users.size
    }
    inner class MainViewHolder(val binding: ItemFragmentBinding) : RecyclerView.ViewHolder(binding.root)

}

