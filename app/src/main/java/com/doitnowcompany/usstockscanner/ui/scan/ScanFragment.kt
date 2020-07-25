package com.doitnowcompany.usstockscanner.ui.scan


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.databinding.FragmentScanBinding



class ScanFragment : Fragment() {

    /**
     * Lazily initialize our [ScanListViewModel].
     * Get a new or existing ViewModel from the [ViewModelProvider].
     */
    private val scanViewModel by lazy { ViewModelProvider(this).get(ScanListViewModel::class.java) }


    /**
     * Inflates the layout with Data Binding, sets its lifecycle owner to the ScanFragment
     * to enable Data Binding to observe LiveData, and sets up the RecyclerView with an adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentScanBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_scan, container, false)

        // Giving the binding access to the ScanListViewModel
        binding.viewmodel = scanViewModel

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Sets the adapter of the RecyclerView with clickHandler lambda that
        // tells the viewModel when our property is clicked
        val adapter =
            TickerListAdapter(
                context,
                TickerListAdapter.OnClickListener {
                    scanViewModel.displayTickerChart(it)
                })

        // Observe the selectedTicker LiveData and Navigate when it isn't null
        // After navigating, call displayTickerChartComplete() so that the ViewModel is ready
        // for another navigation event.
        scanViewModel.selectedTicker.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                // Must find the NavController from the Fragment
                this.findNavController()
                    .navigate(
                        ScanFragmentDirections.actionScanFragmentToChartFragment(
                            it
                        )
                    )
                // Tell the ViewModel we've made the navigate call to prevent multiple navigation
                scanViewModel.displayTickerChartComplete()
            }
        })

        val recyclerView = binding.recyclerview
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        val mDividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            LinearLayoutManager(context).orientation
        )
        ContextCompat.getDrawable(requireContext(), R.drawable.vertical_divider_itemlist)?.let {
            mDividerItemDecoration.setDrawable(
                it
            )
        };

        recyclerView.addItemDecoration(mDividerItemDecoration)

        // Add an observer on the LiveData returned by getAlphabetizedTickers.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        scanViewModel.allTickers.observe(viewLifecycleOwner, Observer { tickers ->
            // Update the cached copy of the tickers in the adapter.
            tickers?.let { adapter.setTickers(it) }
        })
        return binding.root

    }
}
