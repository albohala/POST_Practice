package com.example.post_practice

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.post_practice.databinding.ItemRowBinding
import kotlinx.android.synthetic.main.item_row.view.*

// Fetched from the platform
class RVAdapter(private var users: Users): RecyclerView.Adapter<RVAdapter.ItemViewHolder>() {
    class ItemViewHolder(val binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val user = users[position]
        Log.d("onBindViewHolder", "onResponse: ")

        holder.binding.apply {
            Log.d("onBindViewHolder", "onResponse: ")

            tvName.text = user.name
            tvLocation.text = user.location
            Log.d("onBindViewHolder", "onResponse: $user")

        }
    }

    override fun getItemCount() = users.size

    fun update(users: Users){
        this.users = users
        notifyDataSetChanged()
        Log.d("adapterRVM", "onResponse: $users")

    }
}