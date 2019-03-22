package com.mready.githubandroidrepos.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mready.githubandroidrepos.DTO.SearchItem
import com.mready.githubandroidrepos.R
import com.mready.githubandroidrepos.utils.OnRepositoryClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_search.view.*

class SearchItemsAdapter(private val mOnClick: OnRepositoryClickListener) : PagedListAdapter<SearchItem, SearchItemsAdapter.SearchItemVH>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_search, parent, false)
        view.isFocusable = true
        return SearchItemVH(view)
    }

    override fun onBindViewHolder(holder: SearchItemVH, position: Int) {
        val item = getItem(position) ?: return
        Picasso.get().load(item.owner!!.avatar_url).into(holder.itemView.owner_avatar)
        holder.itemView.name_text.text = item.name
        holder.itemView.owner_text.text = item.owner!!.login
        holder.itemView.description_text.text = item.description
        holder.itemView.setOnClickListener {
            mOnClick.onRepositoryClick(item.owner!!.login, item.name!!)
        }
    }

    inner class SearchItemVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    object DiffCallback: DiffUtil.ItemCallback<SearchItem>(){
        override fun areItemsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: SearchItem, newItem: SearchItem): Boolean = true
    }
}