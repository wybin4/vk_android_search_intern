package com.vk.usersapp.feature.feed.ui

import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vk.usersapp.R
import com.vk.usersapp.feature.feed.model.User

class UserListItemVh(private val resources: Resources, view: View) : RecyclerView.ViewHolder(view) {

    private val avatar: ImageView = view.findViewById(R.id.photo)
    private val title: TextView = view.findViewById(R.id.title)
    private val subtitle: TextView = view.findViewById(R.id.subtitle)

    fun bind(user: User) {
        Glide.with(avatar).load(user.image).into(avatar)
        val ageStringResId = when {
            user.age % 10 == 1 && user.age % 100 != 11 -> R.plurals.year
            user.age % 10 in 2..4 && user.age % 100 !in 12..14 -> R.plurals.year
            else -> R.plurals.year
        }

        val userAgeString = resources.getQuantityString(ageStringResId, user.age, user.age)

        title.text = "${user.firstName} ${user.lastName}, $userAgeString"
        subtitle.text = user.university
    }
}