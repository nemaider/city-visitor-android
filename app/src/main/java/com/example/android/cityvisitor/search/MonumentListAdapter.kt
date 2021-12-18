package com.example.android.cityvisitor.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cityvisitor.search.MonumentListAdapter.MonumentListViewHolder
import com.example.android.cityvisitor.databinding.ListItemBinding
import com.example.android.cityvisitor.network.Monuments

class MonumentListAdapter(val clickListener: MonumentClickListener, val favouriteClickListener: FavouriteMonumentClickListener): ListAdapter<Monuments, MonumentListViewHolder>(DiffCallback){
    companion object DiffCallback : DiffUtil.ItemCallback<Monuments>() {
        override fun areItemsTheSame(oldItem: Monuments, newItem: Monuments): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Monuments, newItem: Monuments): Boolean {
            return oldItem == newItem
        }
    }

    class MonumentListViewHolder(private var binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: MonumentClickListener, monument: Monuments, favouriteClickListener: FavouriteMonumentClickListener) {
            binding.monument = monument
            binding.clickListener = listener
            binding.favClickListener = favouriteClickListener

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MonumentListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater, parent, false)
                return MonumentListViewHolder(binding)
            }
        }
    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs a new [ViewHolder].
     *
     * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
     * to the RecyclerView such as where on the screen it was last drawn during scrolling.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonumentListViewHolder {
        return MonumentListViewHolder.from(parent)

    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs to show an item.
     *
     * The ViewHolder passed may be recycled, so make sure that this sets any properties that
     * may have been set previously.
     */
    override fun onBindViewHolder(holder: MonumentListViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position), favouriteClickListener)
    }
}

class MonumentClickListener(val clickListener: (monument: Monuments) -> Unit) {
    fun onClick(monument: Monuments) = clickListener(monument)

}

class FavouriteMonumentClickListener(val clickFavouriteListener: (monument: Monuments) -> Unit ){
    fun onClickFavourite(monument: Monuments) = clickFavouriteListener(monument)
}
