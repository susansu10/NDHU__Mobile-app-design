package com.example.inventorystarter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.inventorystarter.data.Item
import com.example.inventorystarter.databinding.ItemListItemBinding

class ItemListAdapter(private val onItemClicked: (Item)->Unit):
ListAdapter<Item, ItemListAdapter.ItemViewHolder>(DiffCallback){

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }

    class ItemViewHolder(val binding: ItemListItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.itemName.text = item.itemName
            binding.itemPrice.text = item.itemPrice.toString()
            binding.itemQuantity.text = item.quantityInStock.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListItemBinding.inflate(layoutInflater, parent, false)
        val viewHolder = ItemViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
        holder.itemView.setOnClickListener { onItemClicked(currentItem) }
    }





}