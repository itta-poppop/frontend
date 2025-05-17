package com.ita.poppop.view.empty.search

import androidx.recyclerview.widget.GridLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeSearchBinding
import com.ita.poppop.view.empty.search.holder.HomeSearchAdapter
import com.ita.poppop.view.empty.search.holder.HomeSearchItemDecoration


class HomeSearchFragment: BaseFragment<FragmentHomeSearchBinding>(R.layout.fragment_home_search) {
    override fun initView() {
        binding.apply {
            mtHomeSearch.setNavigationIcon(R.drawable.chevron_left)
            mtHomeSearch.setNavigationOnClickListener {

//                 findNavController(requireParentFragment()).popBackStack() // Navigation Component 사용 시
            }


            val searchList = mutableListOf(0,1,2,3,4,5,6,7,8,9)

            val homeSearchAdapter = HomeSearchAdapter(searchList)
            rvHomeSearchResult.addItemDecoration(HomeSearchItemDecoration())
            rvHomeSearchResult.layoutManager = GridLayoutManager(requireContext(), 2)
            rvHomeSearchResult.adapter = homeSearchAdapter

        }

    }
}