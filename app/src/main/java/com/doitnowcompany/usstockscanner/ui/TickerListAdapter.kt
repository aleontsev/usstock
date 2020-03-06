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
) : RecyclerView.Adapter<TickerListAdapter.TickerViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var tickers = emptyList<TickerEntity>() // Cached copy of words

    inner class TickerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tickerItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TickerViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return TickerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TickerViewHolder, position: Int) {
        val current = tickers[position]
        holder.tickerItemView.text = current.ticker
    }

    internal fun setTickers(tickers: List<TickerEntity>) {
        this.tickers = tickers
        notifyDataSetChanged()
    }

    override fun getItemCount() = tickers.size
}