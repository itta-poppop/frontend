package com.ita.poppop.view.empty.setting.sub

import android.os.Bundle
import android.view.View
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ita.poppop.R
import com.ita.poppop.util.dialog.LogoutDialog
import com.ita.poppop.util.dialog.WithdrawDialog
import com.ita.poppop.view.empty.setting.SettingFragment

class MainSettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
        setupPreferenceClickListeners()
    }

    private fun setupPreferenceClickListeners() {
        setClickListener("pp") { openTermsOfService() }
        setClickListener("set_noti") { openNotificationSettings() }
        setClickListener("logout") { showLogoutDialog() }
        setClickListener("withdraw") { showWithdrawDialog() }
    }

    private fun setClickListener(key: String, action: () -> Unit) {
        findPreference<Preference>(key)?.setOnPreferenceClickListener {
            action()
            true
        }
    }

    private fun openTermsOfService() {
        // TODO: WebView 또는 약관 프래그먼트로 이동
    }

    private fun openNotificationSettings() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_setting, NotificationSettingFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun showLogoutDialog() {
        val dialog = LogoutDialog(requireContext())
        dialog.setItemClickListener(object : LogoutDialog.ItemClickListener {
            override fun onClick(tel: String) {
                // TODO: 로그아웃 처리
            }
        })
        dialog.show()
    }

    private fun showWithdrawDialog() {
        val dialog = WithdrawDialog(requireContext())
        dialog.setItemClickListener(object : WithdrawDialog.ItemClickListener {
            override fun onClick(tel: String) {
                // TODO: 탈퇴 처리
            }
        })
        dialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (parentFragment as? SettingFragment)?.updateToolbarTitle("설정")
    }
}
