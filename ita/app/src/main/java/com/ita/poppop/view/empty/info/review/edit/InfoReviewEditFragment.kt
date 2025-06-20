package com.ita.poppop.view.empty.info.review.edit

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentInfoReviewEditBinding
import com.ita.poppop.view.empty.info.review.detail.InfoReviewDetailFragmentArgs

class InfoReviewEditFragment: BaseFragment<FragmentInfoReviewEditBinding>(R.layout.fragment_info_review_edit) {

    private lateinit var infoReviewEditViewModel: InfoReviewEditViewModel

    private val infoReviewDetailArgs: InfoReviewDetailFragmentArgs by navArgs()

    override fun initView() {
        setupWindowInsets()
        binding.apply {
            infoReviewEditViewModel = ViewModelProvider(this@InfoReviewEditFragment).get(InfoReviewEditViewModel::class.java)

            ivEditReviewBack.setOnClickListener {
                InfoReviewEditDialog(requireContext()).apply {
                    setItemClickListener(object : InfoReviewEditDialog.ItemClickListener {
                        override fun onClick(message: String) {
                            dismiss()
                        }

                        override fun onCancel(message: String) {
                            dismiss()
                            parentFragmentManager.popBackStack()
                        }
                    })
                    show()
                }
            }
        }
    }

}