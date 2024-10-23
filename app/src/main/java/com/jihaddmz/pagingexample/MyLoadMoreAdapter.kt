package com.jihaddmz.pagingexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jihaddmz.pagingexample.databinding.LoadMoreBinding

class MyLoadMoreAdapter(val retry: () -> Unit): LoadStateAdapter<MyLoadMoreAdapter.ViewHolder>() {

    class ViewHolder(val binding: LoadMoreBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.binding.apply {
            prgBarLoadMore.isVisible = loadState is LoadState.Loading
            tvLoadMore.isVisible = loadState is LoadState.Error
            btnLoadMoreRetry.isVisible = loadState is LoadState.Error

            btnLoadMoreRetry.setOnClickListener {
                retry()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}