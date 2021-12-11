package com.acePlus.codeTest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.acePlus.codeTest.base.BaseFragment
import com.acePlus.codeTest.databinding.FragUserDetailBinding
import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.utilities.PASS_DATA

class DetailFragment : BaseFragment() {
    private lateinit var fragmentBinding: FragUserDetailBinding

    override fun initViewBinding() {
        fragmentBinding = FragUserDetailBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val model = arguments?.getSerializable(PASS_DATA) as UserListResponseModel
        with(fragmentBinding) {
            etUserName.setText(model.name)
            etEmail.setText(model.email)
            etAddress.setText("${model.address.suite}, ${model.address.street}, ${model.address.city}, ${model.address.zipcode}")
            etPhone.setText(model.phone)
            etWebsite.setText(model.website)
        }
        return fragmentBinding.root
    }
}