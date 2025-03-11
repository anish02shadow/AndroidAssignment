package com.anish.initapp.data.api

import com.anish.initapp.data.model.BreedResponse
import com.anish.initapp.data.model.FactResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiInterface {
    @GET("breeds")
    suspend fun getBreeds(@Query("limit") limit: Int): BreedResponse

    @GET("facts")
    suspend fun getFacts(@Query("limit") limit: Int,
                         @Query("page") page: Int ): FactResponse
}