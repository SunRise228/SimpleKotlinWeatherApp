package com.example.weatherappwithvova.screen.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappwithvova.R
import com.example.weatherappwithvova.adapter.ViewPagerListAdapter
import com.example.weatherappwithvova.databinding.FragmentDaysBinding
import com.example.weatherappwithvova.databinding.FragmentHoursBinding
import com.example.weatherappwithvova.dtoclass.ViewPagerListItem


class DaysFragment(list : List<ViewPagerListItem>) : Fragment() {

    private lateinit var binding : FragmentDaysBinding
    private lateinit var viewPagerListAdapter : ViewPagerListAdapter
    private val list : List<ViewPagerListItem> = list

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fillRecycleList()
    }

    private fun fillRecycleList() = with(binding) {
        daysSwitchList.layoutManager = LinearLayoutManager(activity)
        viewPagerListAdapter = ViewPagerListAdapter()
        daysSwitchList.adapter = viewPagerListAdapter
        viewPagerListAdapter.submitList(list)
    }

    fun refill(list: List<ViewPagerListItem>) = with(binding) {
        viewPagerListAdapter.submitList(list)
    }

    companion object {
        @JvmStatic
        fun newInstance(list : List<ViewPagerListItem>) =
            DaysFragment(list)
    }
}