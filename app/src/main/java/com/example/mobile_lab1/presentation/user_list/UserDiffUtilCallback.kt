package com.example.mobile_lab1.presentation.user_list

import androidx.recyclerview.widget.DiffUtil
import com.example.mobile_lab1.domain.User

class UserDiffUtilCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return  oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return  oldItem == newItem
    }
}
