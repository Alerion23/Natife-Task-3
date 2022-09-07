package com.wenger.natifetask3.ui.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wenger.natifetask3.data.ResultResponse
import com.wenger.natifetask3.databinding.UserListBinding

class UserAdapter(
    private val onItemClicked: (ResultResponse) -> Unit
) : ListAdapter<ResultResponse, UserAdapter.MyViewHolder>(UserDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = UserListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MyViewHolder(onItemClicked, binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class UserDiffCallBack : DiffUtil.ItemCallback<ResultResponse>() {
        override fun areItemsTheSame(oldItem: ResultResponse, newItem: ResultResponse): Boolean {
            return oldItem.id.uuid == newItem.id.uuid
        }

        override fun areContentsTheSame(oldItem: ResultResponse, newItem: ResultResponse): Boolean {
            return oldItem.name.firstName == newItem.name.firstName &&
                    oldItem.name.lastName == newItem.name.lastName &&
                    oldItem.picture.url == newItem.picture.url
        }
    }

    class MyViewHolder(
        private val onItemClicked: (ResultResponse) -> Unit,
        private val binding: UserListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(currentItem: ResultResponse) {
            binding.name.text = currentItem.name.firstName
            itemView.setOnClickListener {
                onItemClicked(currentItem)
            }
        }
    }
}