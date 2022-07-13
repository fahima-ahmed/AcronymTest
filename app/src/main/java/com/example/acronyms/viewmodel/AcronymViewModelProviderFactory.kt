package com.example.acronyms.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fahima.assessment.repository.AcronymRepository

class AcronymViewModelProviderFactory(
    private val app: Application,
    private val acronymRepository: AcronymRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AcronymViewModel(app, acronymRepository) as T
    }
}