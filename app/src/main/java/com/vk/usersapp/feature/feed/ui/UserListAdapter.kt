package com.vk.usersapp.feature.feed.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.vk.usersapp.R
import com.vk.usersapp.feature.feed.model.User

class UserListAdapter(private val resources: Resources) : PagingDataAdapter<User, UserListItemVh>(USER_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemVh {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vh_user, parent, false)
        return UserListItemVh(resources, view)
    }

    override fun onBindViewHolder(holder: UserListItemVh, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem.lastName == newItem.lastName

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                oldItem == newItem
        }
    }
}