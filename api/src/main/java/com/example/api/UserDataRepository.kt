package com.example.api

import com.example.api.models.RepoTag
import com.example.api.models.User
import com.example.api.models.UserRepo
import com.example.base_core.BaseNetworkDataSource
import com.example.base_core.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UserDataRepository(
    private val api: MainApi,
    private val defaultDispatcher: CoroutineDispatcher,
) : BaseNetworkDataSource(), BaseDataSource {

    override suspend fun getUserDetails(userName: String): Result<User> {
        return withContext(defaultDispatcher) { apiCall { api.getUserDetails(userName) } }
    }

    override suspend fun getUserRepos(userName: String): Result<List<UserRepo>> {
        return withContext(defaultDispatcher) { apiCall { api.getUserRepos(userName) } }
    }

    override suspend fun getRepoDetails(userName: String, repoName: String): Result<UserRepo> {
        return withContext(defaultDispatcher) { apiCall { api.getRepoDetails(userName, repoName) } }
    }

    override suspend fun getRepoTags(userName: String, repoName: String): Result<List<RepoTag>> {
        return withContext(defaultDispatcher) { apiCall { api.getRepoTags(userName, repoName) } }
    }
}