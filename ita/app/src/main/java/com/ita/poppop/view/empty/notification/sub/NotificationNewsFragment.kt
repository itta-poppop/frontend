package com.ita.poppop.view.empty.notification.sub

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentNotificationNewsBinding
import com.ita.poppop.util.SwipeHelper
import com.ita.poppop.view.empty.notification.holder.HomeNotificationAdapter
import com.ita.poppop.view.empty.notification.holder.HomeNotificationItemDecoration

class NotificationNewsFragment : BaseFragment<FragmentNotificationNewsBinding>(R.layout.fragment_notification_news) {

    override fun initView() {
        setupNotificationRecyclerView()
    }

    private fun setupNotificationRecyclerView() {
        val notificationList = (0..9).toMutableList()
        val adapter = HomeNotificationAdapter(requireActivity(), notificationList)

        with(binding.rvNotificationNews) {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
            addItemDecoration(HomeNotificationItemDecoration())

            val itemTouchHelper = ItemTouchHelper(SwipeHelper())
            itemTouchHelper.attachToRecyclerView(this)
        }
    }
}
