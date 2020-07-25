package com.doitnowcompany.usstockscanner.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.databinding.FragmentChartBinding
import com.doitnowcompany.usstockscanner.viewmodel.ChartViewModel
import com.doitnowcompany.usstockscanner.viewmodel.ChartViewModelFactory


class ChartFragment : Fragment() {

    //private val viewModel by lazy { ViewModelProvider(this).get(ChartViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding: FragmentChartBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_chart, container, false)
        binding.lifecycleOwner = this
        val selectedTicker = ChartFragmentArgs.fromBundle(
            requireArguments()
        ).selectedTicker
        val viewModelFactory =
            ChartViewModelFactory(
                selectedTicker,
                application
            )
        binding.chartViewModel = ViewModelProvider(
            this, viewModelFactory).get(ChartViewModel::class.java)

        return binding.root
    }
}