package com.example.api.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepoTag(
    val name: String,
    @SerializedName("zipball_url")
    val zipballUrl: String,
    @SerializedName("tarball_url")
    val tarballUrl: String,
    val commit: Commit,
    @SerializedName("node_id")
    val nodeId: String,
) : Serializable {

    data class Commit(
        val sha: String,
        val url: String,
    ) : Serializable
}
