package com.example.acronyms.api

import com.example.acronyms.model.AcronymResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymAPI {

    @GET("software/acromine/dictionary.py")
    suspend fun getAcronymResult(@Query("sf") shortForm: String): Response<List<AcronymResult>>

}