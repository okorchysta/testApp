package com.example.api

import com.example.api.models.RepoTag
import com.example.api.models.User
import com.example.api.models.UserRepo
import com.example.base_core.Result

interface BaseDataSource {

    suspend fun getUserDetails(userName: String): Result<User>

    suspend fun getUserRepos(userName: String): Result<List<UserRepo>>

    suspend fun getRepoDetails(
        userName: String,
        repoName: String,
    ): Result<UserRepo>

    suspend fun getRepoTags(
        userName: String,
        repoName: String,
    ): Result<List<RepoTag>>
}