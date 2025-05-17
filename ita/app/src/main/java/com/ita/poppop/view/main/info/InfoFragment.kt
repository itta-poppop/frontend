package com.ita.poppop.view.main.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentInfoBinding
import com.ita.poppop.view.main.info.InfoTabPageAdapter
import com.ita.poppop.view.main.info.recommend.InfoRecommendRVAdapter
import com.ita.poppop.view.main.info.recommend.InfoRecommendViewModel
import com.ita.poppop.view.main.info.story.InfoStoryRVAdapter
import com.ita.poppop.view.main.info.story.InfoStoryViewModel


class InfoFragment: BaseFragment<FragmentInfoBinding>(R.layout.fragment_info) {

    private lateinit var infoStoryViewModel: InfoStoryViewModel
    private lateinit var infoRecommendViewModel: InfoRecommendViewModel

    private val infoStoryRVAdapter by lazy {
        InfoStoryRVAdapter()
    }
    private val infoRecommendRVAdapter by lazy {
        InfoRecommendRVAdapter()
    }

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabpageAdapter: InfoTabPageAdapter

    override fun initView() {
        binding.apply {

            /*ibInfoBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }*/

            infoStoryViewModel = ViewModelProvider(this@InfoFragment).get(InfoStoryViewModel::class.java)
            infoRecommendViewModel = ViewModelProvider(this@InfoFragment).get(InfoRecommendViewModel::class.java)

            // 스토리
            binding.rvInfoStory.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = infoStoryRVAdapter
            }
            infoStoryViewModel.getInfoStory()
            infoStoryViewModel.infostoryList.observe(viewLifecycleOwner, Observer { response ->
                infoStoryRVAdapter.submitList(response)

                //binding.emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })

            // 추천
            binding.rvInfoRecommend.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = infoRecommendRVAdapter
            }
            infoRecommendViewModel.getInfoRecommend()
            infoRecommendViewModel.inforecommendList.observe(viewLifecycleOwner, Observer { response ->
                infoRecommendRVAdapter.submitList(response)

                //binding.emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })

            tabLayout = binding.icInfoTablayout.tlInfo
            viewPager2 = binding.icInfoTablayout.vpInfo
            tabpageAdapter = InfoTabPageAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)

            viewPager2.adapter = tabpageAdapter
            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                tab.text = when (position) {
                    0 -> "팝업 정보"
                    1 -> "리뷰"
                    else -> ""
                }
            }.attach()
        }
    }
}