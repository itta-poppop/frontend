package com.ita.poppop.view.empty.upload

import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentUploadReviewBinding

class UploadReviewFragment : BaseFragment<FragmentUploadReviewBinding>(R.layout.fragment_upload_review) {

    override fun initView() {
        setupWindowInsets()
        setupToolbar()

    }

    private fun setupToolbar() {
        binding.mtUploadReview.apply {
            setNavigationIcon(R.drawable.chevron_left)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }
}