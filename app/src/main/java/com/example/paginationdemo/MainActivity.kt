package com.example.paginationdemo

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.paginationdemo.adapters.DogPagerAdapter
import com.example.paginationdemo.adapters.LoadingStateAdapter
import com.example.paginationdemo.databinding.ActivityMainBinding
import com.example.paginationdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var dogPagerAdapter: DogPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dogPagerAdapter = DogPagerAdapter()
        binding.rcvDog.adapter = dogPagerAdapter.withLoadStateHeaderAndFooter(
            header = LoadingStateAdapter { dogPagerAdapter.retry() },
            footer = LoadingStateAdapter { dogPagerAdapter.retry() }
        )

        lifecycleScope.launch {
            mainViewModel.data.collectLatest {
                dogPagerAdapter.submitData(it)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            dogPagerAdapter.refresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}