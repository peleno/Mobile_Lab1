package com.example.mobile_lab1.presentation.user_list

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mobile_lab1.R
import com.example.mobile_lab1.domain.User

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val firstName: TextView = itemView.findViewById(R.id.first_name)
    private val lastName: TextView = itemView.findViewById(R.id.last_name)
    private val userImage: ImageView = itemView.findViewById(R.id.item_image)
    fun bind(user: User) {
        firstName.text = user.firsName
        lastName.text = user.lastName
        Glide.with(itemView)
            .load(user.imageUrl)
            .into(userImage)
    }
}
