package com.ita.poppop.view.empty.story

import android.content.Context
import androidx.core.view.WindowInsetsControllerCompat
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentStoryBinding
import com.ita.poppop.view.empty.story.sub.StoryViewAdapter


class StoryFragment : BaseFragment<FragmentStoryBinding>(R.layout.fragment_story) {

    override fun initView() {
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.vpStory.adapter = StoryViewAdapter(this)
    }

    open fun changeViewPager() {
        binding.vpStory.setCurrentItem(binding.vpStory.currentItem + 1, true)
    }

    private fun setSystemBarAppearance(isLight: Boolean) {
        WindowInsetsControllerCompat(
            requireActivity().window,
            requireActivity().window.decorView
        ).apply {
            isAppearanceLightStatusBars = isLight
            isAppearanceLightNavigationBars = isLight
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setSystemBarAppearance(isLight = false)
    }

    override fun onDetach() {
        super.onDetach()
        setSystemBarAppearance(isLight = true)
    }
}
