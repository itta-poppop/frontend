package com.ita.poppop.view.main.map

import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentMapBinding

class MapFragment: BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {
    override fun initView() {
        binding.apply {
            val bottomSheet = MapBottomsheetFragment()
            bottomSheet.show(parentFragmentManager, bottomSheet.tag)
        }
    }
}