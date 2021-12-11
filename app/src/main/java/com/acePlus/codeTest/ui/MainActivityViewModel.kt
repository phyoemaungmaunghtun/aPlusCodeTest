package com.acePlus.codeTest.ui

import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acePlus.codeTest.model.UserListResponseModel
import com.acePlus.codeTest.repository.DataRepository
import com.acePlus.codeTest.repository.Resource
import kotlinx.coroutines.launch

class MainActivityViewModel @ViewModelInject constructor(
    private val dataRepository: DataRepository,
) : ViewModel() {

    var userList = ObservableField<List<UserListResponseModel>>()

    private val _userListResponseModel = MutableLiveData<Resource<List<UserListResponseModel>>>()
    val userListResponseModel: LiveData<Resource<List<UserListResponseModel>>>
        get() = _userListResponseModel

    fun calApi() {
        _userListResponseModel.value = Resource.Loading()
        viewModelScope.launch {
            _userListResponseModel.value = dataRepository.requestApi()
        }
    }
}
