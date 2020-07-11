package com.doitnowcompany.usstockscanner.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.databinding.RecyclerviewItemBinding
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity


class TickerListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tickers = emptyList<TickerEntity>() // Cached copy of words


    inner class TickerViewHolder(val recyclerViewItemBinding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(recyclerViewItemBinding.root)

    inner class HeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            TYPE_ITEM -> {
                val binding: RecyclerviewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.recyclerview_item, parent, false);
                return TickerViewHolder(binding)
            }
            TYPE_HEADER -> {
                val itemView = inflater.inflate(R.layout.recyclerview_header, parent, false)
                return HeaderViewHolder(itemView)
            }
            else -> {
                val binding: RecyclerviewItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                    R.layout.recyclerview_item, parent, false);
                return TickerViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TickerViewHolder) {

            val current = tickers[position]
            holder.recyclerViewItemBinding.ticker = current
        }

    }
    internal fun setTickers(tickers: List<TickerEntity>) {
        this.tickers = tickers
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            TYPE_HEADER
        } else TYPE_ITEM
    }

    override fun getItemCount() = tickers.size

}



