package com.acePlus.codeTest.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.acePlus.codeTest.R
import com.acePlus.codeTest.base.BaseActivity
import com.acePlus.codeTest.databinding.ActivityMainBinding
import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.repository.Resource
import com.acePlus.codeTest.utilities.PASS_DATA
import com.acePlus.codeTest.utilities.observeLiveData
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var activityBinding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    override fun observeViewModel() {
        observeLiveData(activityViewModel.userListResponseModel, ::handleUserListResponse)
    }

    override fun initViewBinding() {
        activityBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        activityBinding.lifecycleOwner = this
        activityBinding.viewModel = activityViewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityViewModel.calApi()
        with(activityBinding) {
            rvView.layoutManager = LinearLayoutManager(this@MainActivity)
            rvView.adapter = UserListAdapter() { userModel ->
                val bundle = Bundle()
                bundle.putSerializable(PASS_DATA,userModel)
                val fragment = DetailFragment()
                fragment.arguments = bundle
                transactionFragment(fragment)
            }
        }
    }

    private fun handleUserListResponse(userListModelResponse: Resource<List<UserListResponseModel>>) {
        when (userListModelResponse) {
            is Resource.Loading -> loadingDialog.show()
            is Resource.Success -> userListModelResponse.data?.let { list ->
                loadingDialog.dismiss()
                activityViewModel.userList.set(list)
            }
            is Resource.DataError -> {
                loadingDialog.dismiss()
                userListModelResponse.errorMessage?.let { responseDialog.showErrorDialog(it) }
            }
        }
    }

    private fun transactionFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container_meta,
                fragment
            ).addToBackStack(null).commit()
    }
}