package com.ita.poppop.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<T : ViewDataBinding>(@LayoutRes private val layoutRes: Int
) : BottomSheetDialogFragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false  // 바깥 터치로 dismiss 안되게
    }

    override fun onStart() {
        super.onStart()

        val dialog = dialog as BottomSheetDialog
        dialog.setCanceledOnTouchOutside(true)

        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)


        val params = dialog?.window?.attributes
        params?.height = (300 * resources.displayMetrics.density).toInt()
        dialog?.window?.attributes = params

        // BottomSheet 최대 높이 설정
        val displayMetrics = resources.displayMetrics
        val maxHeight = (displayMetrics.heightPixels * 0.85).toInt()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, maxHeight)




    }

    abstract fun initView()
}