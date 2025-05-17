package com.ita.poppop.view.empty.setting

import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentSettingBinding
import com.ita.poppop.view.empty.setting.sub.MainSettingFragment


class SettingFragment: BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override fun initView() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                systemBarInsets.bottom // 하단 패딩만 적용
            )
            insets
        }
        binding.apply {


            childFragmentManager.beginTransaction()
                .replace(R.id.fl_setting, MainSettingFragment())
                .commit()

            mtEditProfile.setNavigationIcon(R.drawable.chevron_left)
            mtEditProfile.setNavigationOnClickListener {
//                 findNavController(requireParentFragment()).popBackStack() // Navigation Component 사용 시
            }



        }

    }
}