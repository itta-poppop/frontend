package com.ita.poppop.view.main.info

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ita.poppop.view.main.info.detail.InfoDetailFragment
import com.ita.poppop.view.main.info.review.InfoReviewFragment

class InfoTabPageAdapter (
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            InfoDetailFragment()
        else
            InfoReviewFragment()
    }
}