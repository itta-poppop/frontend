package com.ita.poppop.view.main.info.review

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentInfoReviewBinding

class InfoReviewFragment: BaseFragment<FragmentInfoReviewBinding>(R.layout.fragment_info_review) {

    private lateinit var infoReviewViewModel: InfoReviewViewModel

    private val infoReviewRVAdapter by lazy {
        InfoReviewRVAdapter()
    }

    override fun initView() {
        binding.apply {
            infoReviewViewModel = ViewModelProvider(this@InfoReviewFragment).get(InfoReviewViewModel::class.java)

            // 리뷰
            binding.rvInfoReview.apply {
                val layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                layoutManager = layoutmanager
                adapter = infoReviewRVAdapter

                val dividerItemDecoration = DividerItemDecoration(context, layoutmanager.orientation)
                addItemDecoration(dividerItemDecoration)
            }
            infoReviewViewModel.getInfoReview()
            infoReviewViewModel.inforeviewList.observe(viewLifecycleOwner, Observer { response ->
                infoReviewRVAdapter.submitList(response)

                //binding.emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })
        }
    }
}