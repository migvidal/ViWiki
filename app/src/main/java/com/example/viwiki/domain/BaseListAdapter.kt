package com.example.viwiki.domain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viwiki.BR
import com.example.viwiki.domain.saved.ListItemClickListener


/**
 * Adapter for the search RecyclerView
 */
class BaseListAdapter<T : BasePage>(
    @LayoutRes val layoutRes: Int,
    val onClickListener: ListItemClickListener
) :
    ListAdapter<T, BaseListAdapter.BaseListViewHolder>(BasicListDiffCallBack()) {


    /**
     * Used by onCreateViewHolder
     */
    class BaseListViewHolder private constructor(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the searchResult data
         */
        fun <T : BasePage> bind(data: T) {
            binding.setVariable(BR.basePage, data)
        }

        companion object {
            /**
             * Creates a new viewHolder that holds the provided ViewGroup
             * @param viewGroup The viewGroup to hold
             */
            fun from(viewGroup: ViewGroup, @LayoutRes layoutRes: Int): BaseListViewHolder {
                val inflater = LayoutInflater.from(viewGroup.context)
                val view = DataBindingUtil.inflate<ViewDataBinding>(
                    inflater,
                    layoutRes,
                    viewGroup,
                    false
                )
                return BaseListViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseListViewHolder {
        return BaseListViewHolder.from(parent, layoutRes)
    }

    override fun onBindViewHolder(holder: BaseListViewHolder, position: Int) {
        val listItem = getItem(position)
        holder.bind(listItem)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(listItem.title)
        }

    }

    class BasicListDiffCallBack<T : BasePage> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem.pageId == newItem.pageId
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return oldItem == newItem
        }

    }
}