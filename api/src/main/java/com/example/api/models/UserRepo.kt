package com.example.api.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserRepo(
    val id: Int,
    @SerializedName("node_id")
    val node_id: String,
    val name: String,
    @SerializedName("full_name")
    val full_name: String,
    val private: Boolean,
    val owner: User,
    @SerializedName("html_url")
    val html_url: String,
    val description: String,
    val fork: Boolean,
    val url: String,
    @SerializedName("open_issues")
    val openIssues: Int,
    val watchers: Int,
    val forks: Int,

    ) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserRepo

        if (id != other.id) return false
        if (node_id != other.node_id) return false
        if (name != other.name) return false
        if (full_name != other.full_name) return false
        if (private != other.private) return false
        if (owner != other.owner) return false
        if (html_url != other.html_url) return false
        if (description != other.description) return false
        if (fork != other.fork) return false
        if (url != other.url) return false
        if (openIssues != other.openIssues) return false
        if (watchers != other.watchers) return false
        if (forks != other.forks) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + node_id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + full_name.hashCode()
        result = 31 * result + private.hashCode()
        result = 31 * result + owner.hashCode()
        result = 31 * result + html_url.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + fork.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + openIssues
        result = 31 * result + watchers
        result = 31 * result + forks
        return result
    }
}