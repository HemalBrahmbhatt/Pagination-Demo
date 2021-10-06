package com.example.paginationdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.paginationdemo.databinding.ErrorItemBinding

class LoadingStateAdapter constructor(private val retry: ()->Unit)
    :LoadStateAdapter<LoadingStateAdapter.LoadingViewHolder>() {

    override fun onBindViewHolder(holder: LoadingViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadingViewHolder {
        val binding = ErrorItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadingViewHolder(binding, retry)
    }

    class LoadingViewHolder(private val binding: ErrorItemBinding, retry: () -> Unit):RecyclerView.ViewHolder(binding.root){

        init {
            binding.btnRetry.setOnClickListener {
                retry()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply {
                progressRetry.isVisible = loadState is LoadState.Loading
                txtRetry.isVisible = loadState !is LoadState.Loading
                btnRetry.isVisible = loadState !is LoadState.Loading
            }
        }
    }


}