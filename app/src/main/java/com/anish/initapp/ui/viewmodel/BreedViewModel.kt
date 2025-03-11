package com.anish.initapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anish.initapp.data.model.Breed
import com.anish.initapp.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BreedViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {
    private val _breeds = MutableLiveData<List<Breed>>()
    val breeds: LiveData<List<Breed>> get() = _breeds

    init {
        fetchBreeds()
    }

    fun fetchBreeds(limit: Int = 10) {
        viewModelScope.launch {
            _breeds.value = repository.getBreeds(limit)
        }
    }

}