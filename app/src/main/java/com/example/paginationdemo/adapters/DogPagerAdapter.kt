package com.example.paginationdemo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat.getDrawable
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.paginationdemo.R
import com.example.paginationdemo.databinding.DogItemBinding
import com.example.paginationdemo.model.Dog

class DogPagerAdapter : PagingDataAdapter<Dog, DogPagerAdapter.DogViewHolder>(DogComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val binding = DogItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class DogViewHolder(private val binding: DogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.apply {
                Glide.with(itemView)
                    .load(dog.url)
                    .placeholder(
                        getDrawable(
                            itemView.context.resources,
                            R.drawable.ic_image,
                            itemView.context.theme
                        )
                    )
                    .error(
                        getDrawable(
                            itemView.context.resources,
                            R.drawable.ic_cancel,
                            itemView.context.theme
                        )
                    )
                    .into(imgItem)
                txtItemId.text = dog.id
            }
        }
    }

    object DogComparator : DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean = oldItem == newItem
    }
}