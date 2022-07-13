package com.example.acronyms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.acronyms.AcronymApplication
import com.example.acronyms.model.AcronymResult
import com.example.acronyms.util.Resource
import com.example.acronyms.util.hasInternetConnection
import com.fahima.assessment.repository.AcronymRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException

class AcronymViewModel(
    private val app: Application,
    private val acronymRepository: AcronymRepository
) : AndroidViewModel(app) {

    val acronymResults: MutableLiveData<Resource<List<AcronymResult>>> = MutableLiveData()

    fun getAcronymResult(acronym: String) = viewModelScope.launch(Dispatchers.IO) {
        safeAcronymResultCall(acronym)
    }

    private suspend fun safeAcronymResultCall(acronym: String) {
        acronymResults.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(app as AcronymApplication)) {
                val response = acronymRepository.getAcronymResult(acronym)
                acronymResults.postValue(Resource.Success(response.body()!!))
            } else {
                acronymResults.postValue(Resource.Error("No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> acronymResults.postValue(Resource.Error("Network Failure"))
                else -> acronymResults.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

}












