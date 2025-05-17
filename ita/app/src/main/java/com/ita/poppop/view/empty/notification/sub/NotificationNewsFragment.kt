package com.ita.poppop.view.empty.notification.sub

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentNotificationNewsBinding
import com.ita.poppop.util.SwipeHelper
import com.ita.poppop.view.empty.notification.holder.HomeNotificationAdapter
import com.ita.poppop.view.empty.notification.holder.HomeNotificationItemDecoration

class NotificationNewsFragment(): BaseFragment<FragmentNotificationNewsBinding>(R.layout.fragment_notification_news) {
    override fun initView() {
        binding.apply {

            val notiList = mutableListOf(0,1,2,3,4,5,6,7,8,9)

            val homeNotificationAdapter = HomeNotificationAdapter(notiList)

            val itemTouchHelper = ItemTouchHelper(SwipeHelper())
            itemTouchHelper.attachToRecyclerView(rvNotificationNews)

            rvNotificationNews.addItemDecoration(HomeNotificationItemDecoration())
            rvNotificationNews.layoutManager = LinearLayoutManager(requireContext())
            rvNotificationNews.adapter = homeNotificationAdapter
        }
    }
}