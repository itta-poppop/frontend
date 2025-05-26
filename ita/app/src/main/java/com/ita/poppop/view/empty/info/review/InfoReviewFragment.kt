package com.ita.poppop.view.empty.info.review

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentInfoReviewBinding
import com.ita.poppop.view.main.MainFragmentDirections
import com.ita.poppop.view.main.favorites.FavoritesRVAdapter
import com.ita.poppop.view.main.home.InfoFragmentDirections

class InfoReviewFragment: BaseFragment<FragmentInfoReviewBinding>(R.layout.fragment_info_review) {

    private lateinit var infoReviewViewModel: InfoReviewViewModel

    private val infoReviewRVAdapter by lazy {
        InfoReviewRVAdapter()
    }

    override fun initView() {
        binding.apply {
            infoReviewViewModel = ViewModelProvider(this@InfoReviewFragment).get(InfoReviewViewModel::class.java)

            // 리뷰
            rvInfoReview.apply {
                val layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                layoutManager = layoutmanager
                adapter = infoReviewRVAdapter

                val dividerItemDecoration = DividerItemDecoration(context, layoutmanager.orientation)
                addItemDecoration(dividerItemDecoration)
            }
            infoReviewViewModel.getInfoReview()
            infoReviewViewModel.inforeviewList.observe(viewLifecycleOwner, Observer { response ->
                infoReviewRVAdapter.submitList(response)

                //emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })

            infoReviewRVAdapter.setInfoReviewItemClickListener(object : InfoReviewRVAdapter.InfoReviewItemClickListener{
                override fun onItemClick(position: Int) {

                    val selectedReview = infoReviewRVAdapter.currentList[position]
                    val parentNavController = requireParentFragment().findNavController()
                    val action = InfoFragmentDirections.actionInfoFragmentToInfoReviewDetailFragment(selectedReview)
                    //val action2 = InfoReviewFragmentDirections.actionInfoReviewFragmentToInfoReviewDetailFragment(selectedReview)
                    parentNavController.navigate(action)

                }
            })
        }
    }
}