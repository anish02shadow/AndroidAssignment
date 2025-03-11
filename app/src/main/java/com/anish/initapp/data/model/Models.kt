package com.anish.initapp.data.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class BreedResponse(
    val data: List<Breed>
)

@Parcelize
data class Breed(
    val breed: String,
    val country: String,
    val origin: String,
    val coat: String,
    val pattern: String
) : Parcelable

data class FactResponse(
    val data: List<Fact>,
    val current_page: Int,
    val last_page: Int
)

@Parcelize
data class Fact(
    val fact: String,
    val length: Int
) : Parcelable