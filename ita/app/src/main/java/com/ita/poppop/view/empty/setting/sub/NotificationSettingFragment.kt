package com.ita.poppop.view.empty.setting.sub

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.ita.poppop.R


class NotificationSettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting, rootKey)
    }
}