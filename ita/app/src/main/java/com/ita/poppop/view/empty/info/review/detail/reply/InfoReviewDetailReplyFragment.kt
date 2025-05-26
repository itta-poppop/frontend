package com.ita.poppop.view.empty.info.review.detail.reply


import android.graphics.Rect
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentInfoReviewDetailReplyBinding
import com.ita.poppop.view.empty.info.review.InfoReviewRVAdapter
import com.ita.poppop.view.empty.info.review.InfoReviewViewModel
import com.ita.poppop.view.empty.info.review.comment.InfoReviewCommentViewModel

class InfoReviewDetailReplyFragment : BaseFragment<FragmentInfoReviewDetailReplyBinding>(R.layout.fragment_info_review_detail_reply){

    private lateinit var infoReviewDetailReplyViewModel: InfoReviewDetailReplyViewModel

    private val infoReviewDetailReplyRVAdapter by lazy {
        InfoReviewDetailReplyRVAdapter()
    }

    override fun initView() {

        binding.apply {

            ivReviewDetailReplyBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            infoReviewDetailReplyViewModel = ViewModelProvider(this@InfoReviewDetailReplyFragment).get(
                InfoReviewDetailReplyViewModel::class.java)

            // 리뷰 대댓글
            rvReviewReply.apply {
                val layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                layoutManager = layoutmanager
                adapter = infoReviewDetailReplyRVAdapter
            }
            infoReviewDetailReplyViewModel.getInfoReviewDetailReply()
            infoReviewDetailReplyViewModel.inforeviewdetailreplyList.observe(viewLifecycleOwner, Observer { response ->
                infoReviewDetailReplyRVAdapter.submitList(response)
            })
            
            tvWriteReply.setOnClickListener {
                editUploadCommentReply.requestFocus()
                // 키보드 열기
                val imm = requireContext().getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
                imm.showSoftInput(editUploadCommentReply, android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT)
            }
            handleReplyUploadArea()
        }
    }

    // 대댓글 게시 입력창 위치 조정
    private fun handleReplyUploadArea() {
        val rootView = binding.root
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)
            val screenHeight = rootView.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            val isKeyboardVisible = keypadHeight > screenHeight * 0.15

            binding.clInfoReviewUploadCommentReply.translationY = if (isKeyboardVisible) {
                -keypadHeight.toFloat()
            } else {
                0f
            }
        }
    }
}
