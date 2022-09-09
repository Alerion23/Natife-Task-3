package com.wenger.natifetask3.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.databinding.UserListBinding

class UserAdapter(
    private val onItemClicked: (User) -> Unit
) : PagingDataAdapter<User, UserAdapter.MyViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(onItemClicked, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.onBind(item)
        }

    }

    class UserDiffCallBack : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.lastName == newItem.lastName &&
                    oldItem.userPhoto == newItem.userPhoto
        }
    }

    class MyViewHolder(
        private val onItemClicked: (User) -> Unit,
        private val binding: UserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(currentItem: User) {
            binding.name.text = currentItem.name
            itemView.setOnClickListener {
                onItemClicked(currentItem)
            }
        }
    }
}