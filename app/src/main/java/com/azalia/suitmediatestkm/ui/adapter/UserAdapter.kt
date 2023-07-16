package com.azalia.suitmediatestkm.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.azalia.suitmediatestkm.data.response.User
import com.azalia.suitmediatestkm.databinding.ItemUserListBinding
import com.azalia.suitmediatestkm.ui.main.MainActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserAdapter: PagingDataAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val itemUserListBinding = ItemUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(itemUserListBinding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(holder.itemView.context, user)
        }
    }

    class UserViewHolder(private val binding: ItemUserListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, user: User) {
            binding.apply {
                tvUserEmail.text = user.email
                val name = user.first_name + user.last_name
                tvUserName.text = name
            }

            Glide.with(itemView.context)
                .load(user.avatar)
                .fitCenter()
                .apply(RequestOptions.circleCropTransform())
                .into(binding.ivUserAvatar)

            itemView.setOnClickListener {
                Intent(context, MainActivity::class.java).also { intent ->
                    intent.putExtra("extra_user", user.first_name + user.last_name)
                    context.startActivity(intent)
                }
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: User,
                newItem: User
            ): Boolean = oldItem == newItem
        }
    }
}