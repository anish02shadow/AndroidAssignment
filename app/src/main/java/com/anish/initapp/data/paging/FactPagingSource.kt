package com.anish.initapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anish.initapp.data.api.CatApiInterface
import com.anish.initapp.data.model.Fact


class FactPagingSource(
    private val api: CatApiInterface
) : PagingSource<Int, Fact>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Fact> {
        // Determine which page to load; default to 1 if not provided.
        val page = params.key ?: 1
        return try {

            val response = api.getFacts(limit = params.loadSize, page = page)

            val prevKey = if (page == 1) null else page - 1
            val nextKey = if (page < response.last_page) page + 1 else null
            LoadResult.Page(
                data = response.data,
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Fact>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}