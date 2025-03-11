package com.anish.initapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.anish.initapp.data.model.Fact
import com.anish.initapp.databinding.ItemFactBinding

class FactAdapter(private val onItemClicked: (Fact) -> Unit) :
    PagingDataAdapter<Fact, FactAdapter.FactViewHolder>(DiffCallback()) {

    inner class FactViewHolder(private val binding: ItemFactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(fact: Fact?) {
            fact?.let{
                binding.textViewFact.text = it.fact
                binding.root.setOnClickListener { onItemClicked(fact) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = ItemFactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<Fact>() {
        override fun areItemsTheSame(oldItem: Fact, newItem: Fact) = oldItem.fact == newItem.fact
        override fun areContentsTheSame(oldItem: Fact, newItem: Fact) = oldItem == newItem
    }
}