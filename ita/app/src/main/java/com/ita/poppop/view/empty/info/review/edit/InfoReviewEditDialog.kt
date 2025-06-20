package com.ita.poppop.view.empty.info.review.edit

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import com.ita.poppop.databinding.DialogInfoReviewEditBinding

class InfoReviewEditDialog(context: Context) : Dialog(context) {

    private var itemClickListener: ItemClickListener? = null
    private lateinit var binding: DialogInfoReviewEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        setupDialog()
        bindView()
    }

    private fun initBinding() {
        binding = DialogInfoReviewEditBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
    }

    private fun setupDialog() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(true)
        setCancelable(true)
    }

    private fun bindView() {
        binding.btnCancel.setOnClickListener {
            itemClickListener?.onCancel("cancel_edit")
            dismiss()
        }

        binding.btnConfirm.setOnClickListener {
            itemClickListener?.onClick("keep_edit")
            dismiss()
        }
    }

    fun setItemClickListener(listener: ItemClickListener) {
        this.itemClickListener = listener
    }

    interface ItemClickListener {
        fun onClick(message: String)
        fun onCancel(message: String)
    }
}