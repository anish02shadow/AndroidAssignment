package com.anish.initapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anish.initapp.data.model.Breed
import com.anish.initapp.databinding.ItemBreedBinding

class BreedAdapter(private val onItemClicked: (Breed) -> Unit) :
    ListAdapter<Breed, BreedAdapter.BreedViewHolder>(DiffCallback()) {

    inner class BreedViewHolder(private val binding: ItemBreedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(breed: Breed) {
            binding.textViewBreed.text = breed.breed
            binding.root.setOnClickListener { onItemClicked(breed) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val binding = ItemBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Breed>() {
        override fun areItemsTheSame(oldItem: Breed, newItem: Breed) = oldItem.breed == newItem.breed
        override fun areContentsTheSame(oldItem: Breed, newItem: Breed) = oldItem == newItem
    }

}