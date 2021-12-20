package com.example.android.cityvisitor

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.cityvisitor.favourites.FavMonumentListAdapter
import com.example.android.cityvisitor.network.Monuments
import com.example.android.cityvisitor.search.MonumentListAdapter

/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Monuments>?) {
    val adapter = recyclerView.adapter as MonumentListAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("favListData")
fun bindFavRecyclerView(recyclerView: RecyclerView, data: List<Monuments>?) {
    val adapter = recyclerView.adapter as FavMonumentListAdapter
    adapter.submitList(data) {
        // scroll the list to the top after the diffs are calculated and posted
        recyclerView.scrollToPosition(0)
    }
}

@BindingAdapter("showOnlyWhenEmpty")
fun View.showOnlyWhenEmpty(data: List<Monuments>?) {
    visibility = when {
        data == null || data.isEmpty() -> View.VISIBLE
        else -> View.GONE
    }
}