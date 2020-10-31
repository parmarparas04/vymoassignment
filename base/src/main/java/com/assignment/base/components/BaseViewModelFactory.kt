package com.assignment.base.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.base.data.repo.RepoManager


class BaseViewModelFactory(val repoManager: RepoManager) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass
            .getConstructor(RepoManager::class.java)
            .newInstance(repoManager)
    }
}