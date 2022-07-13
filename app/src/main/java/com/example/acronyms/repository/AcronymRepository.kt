package com.fahima.assessment.repository

import com.example.acronyms.api.RetrofitInstance
import com.example.acronyms.model.AcronymResult
import retrofit2.Response

class AcronymRepository {
    suspend fun getAcronymResult(acronym: String): Response<List<AcronymResult>> {
        return RetrofitInstance.api.getAcronymResult(acronym)
    }

}