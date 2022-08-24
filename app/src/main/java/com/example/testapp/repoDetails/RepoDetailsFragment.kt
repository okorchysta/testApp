package com.example.testapp.repoDetails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.base.BaseViewModelFragment
import com.example.testapp.base.setVisibility
import com.example.testapp.databinding.FragmentRepoDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val BUNDLE_REPO_NAME = "bundle_key_repo_name"

class RepoDetailsFragment :
    BaseViewModelFragment<RepoDetailsViewModel, FragmentRepoDetailsBinding>(
        FragmentRepoDetailsBinding::inflate) {

    override val viewModel: RepoDetailsViewModel by viewModel()
    private val adapter = TagsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.rvTags?.adapter = adapter

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding?.tvUser?.text = getString(R.string.user_s, user.name)
            binding?.ivAvatar?.let {
                Glide.with(requireContext()).load(user.avatarUrl).into(it)
            }
        }

        viewModel.useRepo.observe(viewLifecycleOwner) {
            binding?.tvRepo?.text = getString(R.string.repository_s, it.name)
            binding?.tvWatchers?.text = getString(R.string.watchers_d, it.watchers)
            binding?.tvForks?.text = getString(R.string.forks_d, it.forks)
        }

        viewModel.tagList.observe(viewLifecycleOwner) {
            adapter.list = it
            binding?.tvEmptyList?.setVisibility(it.isEmpty())
        }
    }

    override fun onResume() {
        super.onResume()
        val repoName = arguments?.getString(BUNDLE_REPO_NAME) ?: ""
        viewModel.getRepoDetails(repoName)
        Log.d("Tag", "onResume = $repoName")
    }

    companion object {

        fun newInstance(repoName: String) = RepoDetailsFragment().apply {
            arguments = bundleOf(BUNDLE_REPO_NAME to repoName)
        }
    }
}