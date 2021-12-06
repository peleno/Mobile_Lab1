package com.example.mobile_lab1.presentation.user_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_lab1.R
import com.example.mobile_lab1.domain.User

// class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
class UserAdapter : ListAdapter<User, UserViewHolder>(UserDiffUtilCallback()) {
//    private val userList: MutableList<User> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.bind(userList[position])
        holder.bind(currentList[position])
    }

//    override fun getItemCount(): Int {
//        return userList.size
//    }

//    fun updateList(userList: List<User>) {
//        this.userList.clear()
//        this.userList.addAll(userList)
//        notifyDataSetChanged()
//    }
}
