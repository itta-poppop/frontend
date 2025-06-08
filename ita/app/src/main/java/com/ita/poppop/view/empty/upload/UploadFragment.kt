package com.ita.poppop.view.empty.upload

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentUploadBinding
import com.ita.poppop.util.bottomsheet.UploadBottomSheet
import com.ita.poppop.view.empty.info.review.InfoReviewRVAdapter
import com.ita.poppop.view.empty.info.review.InfoReviewViewModel

class UploadFragment: BaseFragment<FragmentUploadBinding>(R.layout.fragment_upload) {

    private lateinit var uploadViewModel: UploadViewModel

    private val uploadRVAdapter by lazy {
        UploadRVAdapter()
    }


    override fun initView() {
        setupWindowInsets()
        binding.apply {

            uploadViewModel = ViewModelProvider(this@UploadFragment).get(UploadViewModel::class.java)

            ivUploadBack.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            mcvUploadImg.setOnClickListener{
                showUploadBottomSheet()
            }

            rvUploadImg.apply {
                val layoutmanager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                layoutManager = layoutmanager
                adapter = uploadRVAdapter

            }
            uploadViewModel.getUploadImg()
            uploadViewModel.uploadList.observe(viewLifecycleOwner, Observer { response ->
                uploadRVAdapter.submitList(response)

                //emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })



        }
    }

    private fun showUploadBottomSheet() {
        UploadBottomSheet().show(parentFragmentManager, "upload review img")
    }


}