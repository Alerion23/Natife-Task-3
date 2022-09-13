package com.wenger.natifetask3.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wenger.natifetask3.data.User
import com.wenger.natifetask3.databinding.UserListBinding

private const val PREFER_ITEM_END = 5

class UserAdapter(
    private val onItemClicked: (String) -> Unit,
    private val onListEnd: () -> Unit
) : ListAdapter<User, UserAdapter.MyViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(onItemClicked, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
        if (position == itemCount - PREFER_ITEM_END) {
            onListEnd()
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
        private val onItemClicked: (String) -> Unit,
        private val binding: UserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(currentItem: User) {
            binding.name.text = currentItem.name
            itemView.setOnClickListener {
                val uuid = currentItem.uuid
                onItemClicked(uuid)
            }
        }
    }
}