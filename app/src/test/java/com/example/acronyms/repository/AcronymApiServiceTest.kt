package com.example.acronyms.repository

import com.example.acronyms.api.AcronymAPI
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


 class AcronymApiServiceTest {

    @Test
    fun `verify url is correct`() {
        runBlocking {
            val acronymAPI = Retrofit.Builder()
                .baseUrl("http://www.nactem.ac.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AcronymAPI::class.java)

            val url = acronymAPI.getAcronymResult("aa").raw().request.url
            assertEquals(
                url.toString(),
                "http://www.nactem.ac.uk/software/acromine/dictionary.py?sf=aa"
            )
        }
    }

    @Test
    fun `verify api method is get`() {
        runBlocking {
            val acroAPI = Retrofit.Builder()
                .baseUrl(" http://www.nactem.ac.uk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AcronymAPI::class.java)

            assertEquals(acroAPI.getAcronymResult("x").raw().request.method, "GET")
        }
    }
}