package com.example.weatherappwithvova.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.weatherappwithvova.R

class ViewPagerAdapter(mainFragment : FragmentActivity, private val itemList : List<Fragment>) : FragmentStateAdapter(mainFragment) {
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun createFragment(position: Int): Fragment {
        return itemList[position]
    }

}