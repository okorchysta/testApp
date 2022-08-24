package com.example.testapp.userRepos

import android.os.Bundle
import android.view.View
import com.example.testapp.MainActivity
import com.example.testapp.base.BaseViewModelFragment
import com.example.testapp.base.setVisibility
import com.example.testapp.databinding.FragmentListBinding
import com.example.testapp.repoDetails.RepoDetailsFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.random.Random

class UserReposFragment :
    BaseViewModelFragment<UserReposViewModel, FragmentListBinding>(FragmentListBinding::inflate) {

    override val viewModel: UserReposViewModel by viewModel()
    private var adapter = ReposAdapter {
        (requireActivity() as? MainActivity)?.updateActiveFragment(RepoDetailsFragment.newInstance(
            it.name)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        viewModel.repositoryList.observe(viewLifecycleOwner) {
            adapter.list = it
            binding?.tvEmptyList?.setVisibility(it.isEmpty())
        }
    }

    private fun initViews() {
        binding?.recyclerView?.adapter = adapter
    }


    companion object {
        fun newInstance() = UserReposFragment()
    }

}