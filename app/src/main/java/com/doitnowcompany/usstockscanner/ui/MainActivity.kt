package com.doitnowcompany.usstockscanner.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.databinding.ActivityMainBinding
import com.doitnowcompany.usstockscanner.viewmodel.TickerListViewModel


class MainActivity : AppCompatActivity() {
    // Get a new or existing ViewModel from the ViewModelProvider.
    private  val tickerViewModel by lazy {ViewModelProvider(this).get(TickerListViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding : ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewmodel = tickerViewModel
        binding.lifecycleOwner = this

        val recyclerView = binding.recyclerview
        val adapter = TickerListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager(this).orientation
        )
        ContextCompat.getDrawable(this, R.drawable.vertical_divider_itemlist)?.let {
            mDividerItemDecoration.setDrawable(
                it
            )
        };

        recyclerView.addItemDecoration(mDividerItemDecoration)

        // Add an observer on the LiveData returned by getAlphabetizedTickers.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        tickerViewModel.allTickers.observe(this, Observer { tickers ->
            // Update the cached copy of the tickers in the adapter.
            tickers?.let { adapter.setTickers(it) }
        })

    }
}
