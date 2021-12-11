package com.acePlus.codeTest.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.ui.UserListAdapter

@BindingAdapter("userList")
fun bindUserListAdapter(
    recyclerView: RecyclerView,
    userList: List<UserListResponseModel>?
) {
    val adapter = recyclerView.adapter as UserListAdapter
    adapter.submitData(userList)
}
