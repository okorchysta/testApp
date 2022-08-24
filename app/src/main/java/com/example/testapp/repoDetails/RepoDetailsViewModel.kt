package com.example.testapp.repoDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.api.BaseDataSource
import com.example.api.models.RepoTag
import com.example.api.models.User
import com.example.api.models.UserRepo
import com.example.testapp.USER_NAME
import com.example.testapp.base.BaseViewModel
import kotlinx.coroutines.launch

class RepoDetailsViewModel(
    val repository: BaseDataSource,
) : BaseViewModel() {

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User> = _user

    private val _tagList: MutableLiveData<List<RepoTag>> = MutableLiveData()
    val tagList: LiveData<List<RepoTag>> = _tagList

    private val _userRepo: MutableLiveData<UserRepo> = MutableLiveData()
    val useRepo: LiveData<UserRepo> = _userRepo


    fun getRepoDetails(repoName: String) {
        inProgressMutable.postValue(true)
        viewModelScope.launch {
            val reposDetails = repository.getRepoDetails(USER_NAME, repoName)
            val userDetails = repository.getUserDetails(USER_NAME)
            val userTags = repository.getRepoTags(USER_NAME, repoName)

            handleResponse(reposDetails) {
                _userRepo.postValue(it)
            }
            handleResponse(userDetails) {
                _user.postValue(it)
            }
            handleResponse(userTags) {
                _tagList.postValue(it)
            }

            inProgressMutable.postValue(false)

        }
    }
}