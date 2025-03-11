package com.anish.initapp.data.repository

import com.anish.initapp.data.api.CatApiInterface
import com.anish.initapp.data.model.Breed
import com.anish.initapp.data.model.FactResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatRepository @Inject constructor(private val api: CatApiInterface) {
    suspend fun getBreeds(limit: Int): List<Breed> = api.getBreeds(limit).data
    suspend fun getFacts(limit: Int, page: Int): FactResponse = api.getFacts(limit,page)
}