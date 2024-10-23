package com.jihaddmz.pagingexample

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jihaddmz.pagingexample.databinding.ActivityMainBinding
import com.jihaddmz.pagingexample.databinding.ItemBinding
import com.jihaddmz.simplified_recycler.BaseRecyclerAdapter
import com.jihaddmz.simplified_recycler.simplifiedRecycler
import com.jihaddmz.simplified_requests.SimplifiedRequests
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    private lateinit var adapter: MyPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        adapter = MyPagerAdapter()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        SimplifiedRequests.setUpApi(
            "http://192.168.0.175:8080",
            headers = hashMapOf("Authorization" to "Bearer sh_8u458345834jfjdjfjdsfn")
        )


        lifecycleScope.launch {
            viewModel.moviesList.collect {
                adapter.submitData(it)
            }
        }

        binding.rv.adapter = adapter.withLoadStateFooter(
            MyLoadMoreAdapter {
                adapter.retry()
            }
        )

    }

}