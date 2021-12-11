package com.acePlus.codeTest.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acePlus.codeTest.databinding.UserListItemsBinding
import com.acePlus.codeTest.model.UserListResponseModel

class UserListAdapter(
    private val itemClickCallback: ((UserListResponseModel) -> Unit)?
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private var allUserList: List<UserListResponseModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            UserListItemsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun submitData(userList: List<UserListResponseModel>?) {
        userList?.let {
            allUserList = userList
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allUserList[position])
        holder.itemView.setOnClickListener {
            itemClickCallback?.invoke(allUserList[position])
        }
    }

    override fun getItemCount() = allUserList.size

    class ViewHolder(private val binding: UserListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: UserListResponseModel) {
            with(binding) {
                tvUserName.text = user.name
                tvEmail.text = user.email
            }
        }
    }
}