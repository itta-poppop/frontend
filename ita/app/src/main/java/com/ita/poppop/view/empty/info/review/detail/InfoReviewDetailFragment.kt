package com.ita.poppop.view.empty.info.review.detail

import android.graphics.Rect
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentInfoReviewDetailBinding
import com.ita.poppop.util.bottomsheet.UploadBottomSheet
import com.ita.poppop.view.empty.info.review.comment.InfoReviewCommentDeleteBottomSheet
import com.ita.poppop.view.empty.info.review.comment.InfoReviewCommentRVAdapter
import com.ita.poppop.view.empty.info.review.comment.InfoReviewCommentViewModel

class InfoReviewDetailFragment : BaseFragment<FragmentInfoReviewDetailBinding>(R.layout.fragment_info_review_detail){

    private lateinit var infoReviewCommentViewModel: InfoReviewCommentViewModel

    private val infoReviewCommentRVAdapter by lazy {
        InfoReviewCommentRVAdapter()
    }

    override fun initView() {

        binding.apply {

            ivReviewDetailBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            ivInfoReviewDetailDot.setOnClickListener {
                showInfoReviewDeleteBottomSheet()
            }

            infoReviewCommentViewModel = ViewModelProvider(this@InfoReviewDetailFragment).get(InfoReviewCommentViewModel::class.java)

            // 리뷰 댓글
            rvReviewComment.apply {
                val layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                layoutManager = layoutmanager
                adapter = infoReviewCommentRVAdapter

                val dividerItemDecoration = DividerItemDecoration(context, layoutmanager.orientation)
                addItemDecoration(dividerItemDecoration)
            }
            infoReviewCommentViewModel.getInfoReviewComment()
            infoReviewCommentViewModel.inforeviewcommentList.observe(viewLifecycleOwner, Observer { response ->
                infoReviewCommentRVAdapter.submitList(response)
                /*if (response.reply.isNullOrEmpty()) {
                    rvReviewComment.cl_info_review_comment_reply.visibility = View.GONE
                } else {
                    rvReviewComment.cl_info_review_comment_reply.visibility = View.VISIBLE
                }

                rvReviewComment.visibility = View.VISIBLE // RecyclerView 자체는 항상 보이게
                infoReviewCommentRVAdapter.submitList(response)*/

            })

            infoReviewCommentRVAdapter.setInfoReviewCommentItemClickListener(object : InfoReviewCommentRVAdapter.InfoReviewCommentItemClickListener{
                // 답글 화살표 클릭 시
                override fun onArrowClick(position: Int) {
                    val selectedArrow = infoReviewCommentRVAdapter.currentList[position]
                    val parentNavController = requireParentFragment().findNavController()
                    val action = InfoReviewDetailFragmentDirections.actionInfoReviewDetailFragmentToInfoReviewDetailReplyFragment(selectedArrow)
                    parentNavController.navigate(action)
                }
                // 댓글 점 클릭 시
                override fun onDotClick(position: Int) {
                    InfoReviewCommentDeleteBottomSheet().show(parentFragmentManager, "delete comment")
                }
            })

            handleCommentUploadArea()
        }
    }

    private fun showInfoReviewDeleteBottomSheet() {
        InfoReviewDeleteBottomSheet().show(parentFragmentManager, "delete review")
    }

    // 댓글 게시 입력창 위치 조정
    private fun handleCommentUploadArea() {
        val rootView = binding.root
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            val isKeyboardVisible = keypadHeight > screenHeight * 0.15

            binding.clInfoReviewUploadComment.translationY = if (isKeyboardVisible) {
                -keypadHeight.toFloat()
            } else {
                0f
            }
        }
    }
}
