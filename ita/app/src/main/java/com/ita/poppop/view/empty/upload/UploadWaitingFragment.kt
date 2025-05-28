package com.ita.poppop.view.empty.upload

import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentUploadWaitingBinding
import com.ita.poppop.util.bottomsheet.UploadBottomSheet


class UploadWaitingFragment : BaseFragment<FragmentUploadWaitingBinding>(R.layout.fragment_upload_waiting) {

    override fun initView() {
        setupWindowInsets()
        setupToolbar()
        setClickListener()
    }
    private fun setClickListener() {
        binding.button.setOnClickListener{
            showUploadBottomSheet()
        }
    }

    private fun setupToolbar() {
        binding.mtUploadWaiting.apply {
            setNavigationIcon(R.drawable.icon_x_close_b)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }

    private fun showUploadBottomSheet() {
        UploadBottomSheet().show(
            parentFragmentManager,
            UploadBottomSheet
        )
    }

    companion object {
        private const val UploadBottomSheet = "UploadBottomSheet"
    }
}