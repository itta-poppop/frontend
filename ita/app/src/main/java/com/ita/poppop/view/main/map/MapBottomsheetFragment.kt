package com.ita.poppop.view.main.map

import android.os.Bundle
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseBottomSheetFragment
import com.ita.poppop.databinding.FragmentMapBottomsheetBinding

class MapBottomsheetFragment: BaseBottomSheetFragment<FragmentMapBottomsheetBinding>(R.layout.fragment_map_bottomsheet) {
    private lateinit var mapViewModel: MapViewModel

    private val mapRVAdapter by lazy {
        MapRVAdapter()
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun initView() {
        binding.apply {
            mapViewModel = ViewModelProvider(this@MapBottomsheetFragment).get(MapViewModel::class.java)
            linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


            binding.rvMap.apply {
                layoutManager = linearLayoutManager
                adapter = mapRVAdapter

                val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(dividerItemDecoration)
            }

            mapViewModel.getMap()
            mapViewModel.mapList.observe(viewLifecycleOwner, Observer { response ->
                mapRVAdapter.submitList(response)
            })
        }
    }

}