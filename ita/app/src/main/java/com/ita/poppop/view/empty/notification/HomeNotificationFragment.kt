package com.ita.poppop.view.empty.notification

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeNotificationBinding
import com.ita.poppop.view.empty.notification.holder.HomeNotificationAdapter
import com.ita.poppop.view.empty.notification.holder.HomeNotificationItemDecoration
import com.ita.poppop.view.empty.notification.sub.NotificationNewsFragment
import com.ita.poppop.view.empty.notification.sub.NotificationNoticeFragment


class HomeNotificationFragment: BaseFragment<FragmentHomeNotificationBinding>(R.layout.fragment_home_notification) {
    override fun initView() {
        binding.apply {
            mtHomeNotification.setNavigationIcon(R.drawable.chevron_left)
            mtHomeNotification.setNavigationOnClickListener {

//                 findNavController(requireParentFragment()).popBackStack() // Navigation Component 사용 시
            }
            loadFragment(NotificationNewsFragment())
            tlNotification.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {loadFragment(NotificationNewsFragment())}
                        else -> {loadFragment(NotificationNoticeFragment())}
                    }
                }
                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

            })

//



        }

    }
    private fun loadFragment(fragment: Fragment): Boolean {
        childFragmentManager.beginTransaction()
            .replace(R.id.fl_notification, fragment)
            .commit()
        return true
    }
}