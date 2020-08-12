package com.doitnowcompany.usstockscanner.ui.chart

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.doitnowcompany.usstockscanner.R
import com.doitnowcompany.usstockscanner.databinding.FragmentChartBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize
import com.google.android.gms.ads.AdView


class ChartFragment : Fragment() {

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

        //Adding adaptive adBanner
        val adView = AdView(activity)
        adView.adUnitId = getString(R.string.admob_application_block)
        //Getting size of Ad
        adView.adSize = getAdSize(binding)

        binding.adViewContainer.addView(adView)
        //Loading Ad
        adView.loadAd(AdRequest.Builder().build())
        return binding.root
    }

    private fun getAdSize(binding: FragmentChartBinding):AdSize{

        val outMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(outMetrics)

        val density = outMetrics.density
        var adWidthPixels = binding.adViewContainer.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
    }
}