package com.ita.poppop.view.empty.setting

import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentSettingBinding
import com.ita.poppop.view.empty.setting.sub.MainSettingFragment


class SettingFragment: BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun initView() {
        setupWindowInsets()
        setupToolbar()
        loadMainSettingFragment()
    }

    private fun setupToolbar() {
        binding.mtEditProfile.apply {
            setNavigationIcon(R.drawable.chevron_left)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }

    private fun loadMainSettingFragment() {
        if (childFragmentManager.findFragmentById(R.id.fl_setting) == null) {
            childFragmentManager.beginTransaction()
                .replace(R.id.fl_setting, MainSettingFragment())
                .commit()
        }
    }

    // 툴바 제목을 업데이트하는 메서드
    fun updateToolbarTitle(title: String) {
        binding.tvToolbarTitle.text = title
    }
}