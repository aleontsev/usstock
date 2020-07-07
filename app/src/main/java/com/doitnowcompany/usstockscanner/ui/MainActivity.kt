package com.doitnowcompany.usstockscanner.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.doitnowcompany.usstockscanner.databinding.ActivityMainBinding
import com.doitnowcompany.usstockscanner.viewmodel.TickerListViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var tickerViewModel: TickerListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)

        val scan = binding.scan
        val recyclerView = binding.recyclerview
        val adapter = TickerListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager(this).orientation
        )
        recyclerView.addItemDecoration(mDividerItemDecoration)


        // Get a new or existing ViewModel from the ViewModelProvider.
        tickerViewModel = ViewModelProvider(this).get(TickerListViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedTickers.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        tickerViewModel.allTickers.observe(this, Observer { tickers ->
            // Update the cached copy of the tickers in the adapter.
            tickers?.let { adapter.setTickers(it) }
        })

        // When scan is clicked tickers loaded from network
        scan.setOnClickListener{
            tickerViewModel.refreshTickers()
        }

    }
}
