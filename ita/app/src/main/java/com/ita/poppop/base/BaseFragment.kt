package com.ita.poppop.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes val layoutRes: Int)
    : Fragment() {
    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 인플레이션 방식 변경
        binding = DataBindingUtil.inflate(
            layoutInflater,
            layoutRes,
            null,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = this@BaseFragment

        initView()
        super.onViewCreated(view, savedInstanceState)
    }
    open fun setupWindowInsets(){
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())

            // IME(키보드)가 표시되어 있는지 확인
            val isKeyboardVisible = imeInsets.bottom > 0

            view.setPadding(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                // 키보드가 올라와 있으면 패딩을 0으로, 아니면 시스템바 인셋 적용
                if (isKeyboardVisible) 0 else systemBarInsets.bottom
            )
            insets
        }
    }
    open fun handleBackNavigation() {
        when {
            hasChildFragments() -> childFragmentManager.popBackStack()
            else -> findNavController(requireParentFragment()).popBackStack()
        }
    }

    open fun hasChildFragments(): Boolean {
        return childFragmentManager.backStackEntryCount > 0
    }

    abstract fun initView()
}