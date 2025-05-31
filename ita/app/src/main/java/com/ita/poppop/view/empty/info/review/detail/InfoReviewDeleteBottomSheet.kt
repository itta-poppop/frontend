package com.ita.poppop.view.empty.info.review.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ita.poppop.databinding.FragmentInfoReviewDeleteBottomSheetBinding

class InfoReviewDeleteBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentInfoReviewDeleteBottomSheetBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoReviewDeleteBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        binding.clReviewEdit.setOnClickListener {
            //리뷰 수정 화면 이동
        }
        binding.clReviewDelete.setOnClickListener {
            showReviewDeleteDialog()
        }
    }

    private fun showReviewDeleteDialog() {
        InfoReviewDeleteDialog(requireContext()).apply {
            setItemClickListener(object : InfoReviewDeleteDialog.ItemClickListener {
                override fun onClick(tel: String) {
                }
            })
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}