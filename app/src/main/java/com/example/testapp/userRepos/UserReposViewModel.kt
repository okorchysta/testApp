package com.example.testapp.userRepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.api.BaseDataSource
import com.example.api.models.UserRepo
import com.example.testapp.USER_NAME
import com.example.testapp.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserReposViewModel(
    private val repository: BaseDataSource,
) : BaseViewModel() {

    private val _repositoryList: MutableLiveData<List<UserRepo>> = MutableLiveData()
    val repositoryList: LiveData<List<UserRepo>> = _repositoryList


    init {
        inProgressMutable.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getUserRepos(USER_NAME)
            handleResponse(response) {
                _repositoryList.postValue(it)
            }
            inProgressMutable.postValue(false)

        }
    }
}