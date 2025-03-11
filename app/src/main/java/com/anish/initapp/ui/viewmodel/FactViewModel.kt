package com.anish.initapp.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.anish.initapp.data.api.CatApiInterface
import com.anish.initapp.data.model.Fact
import com.anish.initapp.data.paging.FactPagingSource
import com.anish.initapp.data.repository.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactViewModel @Inject constructor(private val api: CatApiInterface) : ViewModel() {

    //This commented code is an attempt to custom paging but realised pagination is just better
//    private val _facts = MutableLiveData<List<Fact>>(emptyList())
//    val facts: LiveData<List<Fact>> get() = _facts
//
//    private var currentPage = 1
//    private var lastPage = Int.MAX_VALUE
//    private var isLoading = false
//
//    init {
//        fetchFacts()
//    }
//
//    fun fetchFacts(limit: Int = 10) {
//        if(isLoading) return
//        if(currentPage > lastPage) return
//
//        isLoading = true
//        viewModelScope.launch {
//            val response = repository.getFacts(limit = limit, page = currentPage)
//
//            val currentList = _facts.value?.toMutableList() ?: mutableListOf()
//
//            currentList.addAll(response.data)
//            _facts.value = currentList
//
//            currentPage++
//            lastPage = response.last_page
//            isLoading = false
//        }
//    }

    // Pager produces a Flow<PagingData<Fact>> that emits new data as pages are loaded.
    val factFlow = Pager(
        config = PagingConfig(
            pageSize = 10,
            maxSize = 30,  //Change to modify in memory cache
            enablePlaceholders = false
        )
    ) {
        FactPagingSource(api)
    }.flow.cachedIn(viewModelScope)
}