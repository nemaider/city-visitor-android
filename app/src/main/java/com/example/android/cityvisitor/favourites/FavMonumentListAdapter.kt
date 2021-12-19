package com.example.android.cityvisitor.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cityvisitor.databinding.FavListItemBinding
import com.example.android.cityvisitor.favourites.FavMonumentListAdapter.FavMonumentListViewHolder
import com.example.android.cityvisitor.network.Monuments

class FavMonumentListAdapter(val clickListener: FavMonumentClickListener, val unfavouriteClickListener: UnfavouriteMonumentClickListener): ListAdapter<Monuments, FavMonumentListViewHolder>(DiffCallback){
    companion object DiffCallback : DiffUtil.ItemCallback<Monuments>() {
        override fun areItemsTheSame(oldItem: Monuments, newItem: Monuments): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Monuments, newItem: Monuments): Boolean {
            return oldItem == newItem
        }
    }

    class FavMonumentListViewHolder(private var binding: FavListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: FavMonumentClickListener, monument: Monuments, unfavouriteClickListener: UnfavouriteMonumentClickListener) {
            binding.monument = monument
//            binding.fav_clickListener = listener
            binding.clickListener = listener
            binding.unfavClickListener = unfavouriteClickListener

            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FavMonumentListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavListItemBinding.inflate(layoutInflater, parent, false)
                return FavMonumentListViewHolder(binding)
            }
        }
    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs a new [ViewHolder].
     *
     * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
     * to the RecyclerView such as where on the screen it was last drawn during scrolling.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMonumentListViewHolder {
        return FavMonumentListViewHolder.from(parent)

    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs to show an item.
     *
     * The ViewHolder passed may be recycled, so make sure that this sets any properties that
     * may have been set previously.
     */
    override fun onBindViewHolder(holder: FavMonumentListViewHolder, position: Int) {
        holder.bind(clickListener, getItem(position), unfavouriteClickListener)
    }
}

class FavMonumentClickListener(val clickListener: (monument: Monuments) -> Unit) {
    fun onClick(monument: Monuments) = clickListener(monument)

}

class UnfavouriteMonumentClickListener(val clickUnfavouriteListener: (monument: Monuments) -> Unit ){
    fun onClickUnfavourite(monument: Monuments) = clickUnfavouriteListener(monument)
}
