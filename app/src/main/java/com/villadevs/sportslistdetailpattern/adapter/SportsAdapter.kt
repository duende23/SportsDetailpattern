package com.villadevs.sportslistdetailpattern.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.villadevs.sportslistdetailpattern.databinding.SportsListItemBinding
import com.villadevs.sportslistdetailpattern.model.Sport

class SportsAdapter (private val onItemClicked: (Sport) -> Unit) : ListAdapter<Sport, SportsAdapter.SportsViewHolder>(DiffCallback) {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        context = parent.context
        return SportsViewHolder(SportsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(current)
        }
        holder.bind(current, context)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Sport>() {
            override fun areItemsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return (oldItem.id == newItem.id || oldItem.titleResourceId == newItem.titleResourceId || oldItem.subTitleResourceId == newItem.subTitleResourceId)
            }

            override fun areContentsTheSame(oldItem: Sport, newItem: Sport): Boolean {
                return oldItem == newItem
            }
        }
    }

    class SportsViewHolder(private var binding: SportsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(sport: Sport, context: Context) {
            binding.title.text = context.getString(sport.titleResourceId)
            binding.subTitle.text = context.getString(sport.subTitleResourceId)
            // Load the images into the ImageView using the Coil library.
            binding.sportsImage.load(sport.imageResourceId)
        }
    }
}
