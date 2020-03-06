package com.doitnowcompany.usstockscanner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.viewmodel.TickerListViewModel
import kotlinx.coroutines.channels.ticker

class MainActivity : AppCompatActivity() {

    private lateinit var tickerViewModel: TickerListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = TickerListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        tickerViewModel = ViewModelProvider(this).get(TickerListViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        tickerViewModel.allTickers.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setTickers(it) }
        })
    }
}
