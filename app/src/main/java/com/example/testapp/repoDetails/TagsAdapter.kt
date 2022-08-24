package com.example.testapp.repoDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.models.RepoTag
import com.example.api.models.UserRepo
import com.example.testapp.base.setVisibility
import com.example.testapp.databinding.ItemRepoBinding

class TagsAdapter(
    _list: List<RepoTag> = emptyList(),
) : ListAdapter<RepoTag, TagsAdapter.ViewHolder>(callback) {

    var list: List<RepoTag>
        get() = currentList
        set(value) {
            submitList(value)
        }

    init {
        list = _list
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: RepoTag) {
            binding.tvTitle.text = "Tag: ${tag.name}; Sha: ${tag.commit.sha}"
            binding.tvIssues.setVisibility(false)
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<RepoTag>() {
            override fun areItemsTheSame(oldItem: RepoTag, newItem: RepoTag) =
                oldItem.nodeId == newItem.nodeId

            override fun areContentsTheSame(oldItem: RepoTag, newItem: RepoTag) =
                oldItem == newItem
        }
    }
}