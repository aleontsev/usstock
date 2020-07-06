package com.doitnowcompany.usstockscanner.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.db.entity.TickerEntity


class TickerListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tickers = emptyList<TickerEntity>() // Cached copy of words

    inner class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tickerItemView: TextView = itemView.findViewById(R.id.text_ticker)
        val priceItemView: TextView = itemView.findViewById(R.id.text_price)
        val changeItemView: TextView = itemView.findViewById(R.id.text_change)
        val volumeItemView: TextView = itemView.findViewById(R.id.text_volume)
    }

    inner class HeaderViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView) {
        val view_ticker: TextView = headerView.findViewById(R.id.ticker)
        val view_price: TextView = headerView.findViewById(R.id.price)
        val view_change: TextView = headerView.findViewById(R.id.change)
        val view_voulme: TextView = headerView.findViewById(R.id.volume)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_ITEM) {
            val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
            return TickerViewHolder(itemView)
        } else if (viewType == TYPE_HEADER) {
            val itemView = inflater.inflate(R.layout.recyclerview_header, parent, false)
            return HeaderViewHolder(itemView)
        }
        else{
            val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
            return TickerViewHolder(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is TickerViewHolder) {

            val current = tickers[position]
            holder.tickerItemView.text = current.ticker
            holder.priceItemView.text = current.last_price.toString()
            holder.changeItemView.text = current.change.toString()
            holder.volumeItemView.text = current.volume.toString()
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



