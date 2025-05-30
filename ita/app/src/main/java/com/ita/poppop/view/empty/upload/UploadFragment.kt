package com.ita.poppop.view.empty.upload

import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentUploadBinding
import com.ita.poppop.util.bottomsheet.UploadBottomSheet

class UploadFragment: BaseFragment<FragmentUploadBinding>(R.layout.fragment_upload) {


    override fun initView() {
        binding.apply {

            ivUploadBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            mcvUploadImg.setOnClickListener{
                showUploadBottomSheet()
            }

        }
    }

    private fun showUploadBottomSheet() {
        UploadBottomSheet().show(parentFragmentManager, "upload review img")
    }


}