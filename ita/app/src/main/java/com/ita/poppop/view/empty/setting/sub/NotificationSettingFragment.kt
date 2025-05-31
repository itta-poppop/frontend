package com.ita.poppop.view.empty.setting.sub

import android.os.Bundle
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ita.poppop.R
import com.ita.poppop.view.empty.setting.SettingFragment

class NotificationSettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting_notification, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateToolbarTitle("알림 설정")
        setupPreferenceClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        updateToolbarTitle("설정")
    }

    private fun updateToolbarTitle(title: String) {
        (parentFragment as? SettingFragment)?.updateToolbarTitle(title)
    }

    private fun setupPreferenceClickListeners() {
        setClickListener("pp") { navigateToNotificationSetting() }
        // 추가 설정 항목이 있다면 여기에서 계속 bind 가능
    }

    private fun setClickListener(key: String, action: () -> Unit) {
        findPreference<Preference>(key)?.setOnPreferenceClickListener {
            action()
            true
        }
    }

    private fun navigateToNotificationSetting() {
        // TODO: 알림 관련 세부 설정 페이지(WebView 또는 Fragment)로 이동
    }
}
