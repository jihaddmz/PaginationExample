package com.jihaddmz.pagingexample

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jihaddmz.simplified_requests.SimplifiedRequests
import kotlinx.coroutines.delay

class MyPagingSource : PagingSource<Int, PagingDTO>() {

    override fun getRefreshKey(state: PagingState<Int, PagingDTO>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingDTO> {
        delay(2000)
        val currentPage = params.key ?: 1
        val prevPage = if (params.key == 1) null else params.key?.minus(1)
        var result: LoadResult<Int, PagingDTO>? = null
        SimplifiedRequests.callGet<List<PagingDTO>>("person_page",
            hashMapOf("page" to "$currentPage", "pageSize" to params.loadSize.toString()),
            onSuccess = {
                result = LoadResult.Page(data = it, prevKey = prevPage ?: -1, currentPage.plus(1))
            },
            onFailed = {
                result = LoadResult.Error(it)
            })

        return result!!
    }
}