package com.jihaddmz.pagingexample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jihaddmz.pagingexample.databinding.ItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyPagerAdapter : PagingDataAdapter<PagingDTO, MyPagerAdapter.ViewHolder>(differCallback) {

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<PagingDTO>() {
            override fun areItemsTheSame(oldItem: PagingDTO, newItem: PagingDTO): Boolean {
                return oldItem.uri == newItem.uri
            }

            override fun areContentsTheSame(oldItem: PagingDTO, newItem: PagingDTO): Boolean {
                return oldItem.uri == newItem.uri
            }
        }
    }

    class ViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.iv.load(getItem(position)?.uri)
        holder.binding.tv.text = "The nb is ${getItem(position)?.nb.toString()}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
}