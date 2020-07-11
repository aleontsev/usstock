package com.doitnowcompany.usstockscanner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.databinding.ActivityChartBinding
import com.doitnowcompany.usstockscanner.viewmodel.ChartViewModel


class ChartActivity : AppCompatActivity() {

    private  val viewModel by lazy {ViewModelProvider(this).get(ChartViewModel::class.java)}

    override fun onCreate(savedInstanceState: Bundle?) {

        val binding : ActivityChartBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_chart)
        binding.lifecycleOwner = this
        binding.chartViewModel = viewModel

        val view = binding.root
        super.onCreate(savedInstanceState)
        setContentView(view)


    }
}