package com.jihaddmz.pagingexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class MyViewModel: ViewModel() {

    val moviesList = Pager(PagingConfig(10, initialLoadSize = 10)) {
        MyPagingSource()
    }.flow.cachedIn(viewModelScope)


}