package com.ita.poppop.view.empty.notification

import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeNotificationBinding
import com.ita.poppop.view.empty.notification.sub.NotificationNewsFragment
import com.ita.poppop.view.empty.notification.sub.NotificationNoticeFragment

class NotificationFragment : BaseFragment<FragmentHomeNotificationBinding>(
    R.layout.fragment_home_notification
), TabLayout.OnTabSelectedListener {

    companion object {
        private const val NEWS_TAB_POSITION = 0
        private const val NOTICE_TAB_POSITION = 1
    }

    override fun initView() {
        setupWindowInsets()
        setupToolbar()
        setupTabLayout()
        loadInitialFragment()
    }


    private fun setupToolbar() {
        binding.mtHomeNotification.apply {
            setNavigationIcon(R.drawable.chevron_left)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }

    private fun setupTabLayout() {
        binding.tlNotification.addOnTabSelectedListener(this)
    }

    private fun loadInitialFragment() {
        loadFragment(createNewsFragment())
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        val fragment = when (tab?.position) {
            NEWS_TAB_POSITION -> createNewsFragment()
            NOTICE_TAB_POSITION -> createNoticeFragment()
            else -> createNewsFragment()
        }
        loadFragment(fragment)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        // 재선택 시 특별한 동작 없음
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        // 선택 해제 시 특별한 동작 없음
    }

    private fun createNewsFragment(): Fragment = NotificationNewsFragment()

    private fun createNoticeFragment(): Fragment = NotificationNoticeFragment()


    private fun loadFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_notification, fragment)
            .commit()
    }

    override fun onDestroy() {
        binding.tlNotification.removeOnTabSelectedListener(this)
        super.onDestroy()
    }
}