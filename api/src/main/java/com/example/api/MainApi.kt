package com.example.api

import com.example.api.models.RepoTag
import com.example.api.models.User
import com.example.api.models.UserRepo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("/users/{userName}")
    suspend fun getUserDetails(@Path("userName") userName: String): Response<User>

    @GET("/users/{userName}/repos")
    suspend fun getUserRepos(@Path("userName") userName: String): Response<List<UserRepo>>

    @GET("/repos/{userName}/{repoName}")
    suspend fun getRepoDetails(
        @Path("userName") userName: String,
        @Path("repoName") repoName: String,
    ): Response<UserRepo>

    @GET("/repos/{userName}/{repoName}/tags")
    suspend fun getRepoTags(
        @Path("userName") userName: String,
        @Path("repoName") repoName: String,
    ): Response<List<RepoTag>>

}
