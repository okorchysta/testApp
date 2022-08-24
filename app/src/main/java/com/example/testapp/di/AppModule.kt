package com.example.testapp.di

import com.example.testapp.repoDetails.RepoDetailsViewModel
import com.example.testapp.userRepos.UserReposViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    // ViewModel for Search View
    viewModel { RepoDetailsViewModel(get()) }

    // ViewModel for Result View
    viewModel { UserReposViewModel(get()) }

}