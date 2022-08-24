package com.example.testapp.userRepos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.api.models.UserRepo
import com.example.testapp.R
import com.example.testapp.databinding.ItemRepoBinding

class ReposAdapter(
    _list: List<UserRepo> = emptyList(),
    private val onClickListener: (UserRepo) -> Unit,
) : ListAdapter<UserRepo, ReposAdapter.ViewHolder>(callback) {


    var list: List<UserRepo>
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
        return ViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)) /* CHANGED */
        holder.itemView.setOnClickListener {
            onClickListener.invoke(getItem(position))
        }
    }

    class ViewHolder(val binding: ItemRepoBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: UserRepo) {
            binding.tvTitle.text = repo.name
            binding.tvIssues.text = context.getString(R.string.issues_d, repo.openIssues)
        }
    }

    companion object {
        val callback = object : DiffUtil.ItemCallback<UserRepo>() {
            override fun areItemsTheSame(oldItem: UserRepo, newItem: UserRepo) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserRepo, newItem: UserRepo) =
                oldItem == newItem
        }
    }
}