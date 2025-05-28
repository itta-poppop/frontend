package com.ita.poppop.view.empty.search

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeSearchBinding
import com.ita.poppop.util.SwipeHelper
import com.ita.poppop.view.empty.search.holder.HomeSearchAdapter
import com.ita.poppop.view.empty.search.holder.HomeSearchItemDecoration


class SearchFragment: BaseFragment<FragmentHomeSearchBinding>(R.layout.fragment_home_search) {
    override fun initView() {
        setupWindowInsets()
        setupToolbar()
        setupSearchRecyclerView()
        setupSearchInput()
        setSearchFocus()

    }
    private fun setupSearchInput() {
        binding.etSearch.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                val inputText = binding.etSearch.text.toString()
                Toast.makeText(requireContext(), "inputText : ${inputText}, keyCode : ${keyCode}", Toast.LENGTH_SHORT).show()
                true // 이벤트 소비
            } else {
                false // 다른 키는 넘김
            }
        }
    }

    private fun setSearchFocus(){
        binding.etSearch.requestFocus()

        // 키보드 자동 표시
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun setupToolbar() {
        binding.mtHomeSearch.apply {
            setNavigationIcon(R.drawable.chevron_left)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }

    private fun setupSearchRecyclerView(){
        val searchList = (0..9).toMutableList()
        val adapter = HomeSearchAdapter(searchList)

        with(binding.rvHomeSearchResult) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            this.adapter = adapter
            addItemDecoration(HomeSearchItemDecoration())

            val itemTouchHelper = ItemTouchHelper(SwipeHelper())
            itemTouchHelper.attachToRecyclerView(this)
        }
    }
}