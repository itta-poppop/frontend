package com.ita.poppop.view.empty.story.sub


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class StoryViewAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val storyItems = listOf(
        "데이터 1", "데이터 2", "데이터 3", "데이터 4", "데이터 5",
        "데이터 6", "데이터 7", "데이터 8", "데이터 9", "데이터 10"
    )

    override fun getItemCount(): Int = storyItems.size

    override fun createFragment(position: Int): Fragment =
        StoryViewFragment.newInstance(storyItems[position])
}

