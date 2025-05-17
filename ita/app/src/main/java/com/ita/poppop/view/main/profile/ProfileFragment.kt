package com.ita.poppop.view.main.profile

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeBinding
import com.ita.poppop.databinding.FragmentProfileBinding
import com.ita.poppop.view.main.MainFragmentDirections
import com.ita.poppop.view.main.profile.holder.ProfileReviewAdapter
import com.ita.poppop.view.main.profile.holder.ProfileReviewItemDecoration

class ProfileFragment: BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun initView() {
        binding.apply {

            val myList = mutableListOf(0,1,2,3,4,5)

            val historyAdapter = ProfileReviewAdapter(myList)
            rvProfileMyReview.addItemDecoration(ProfileReviewItemDecoration())
            rvProfileMyReview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvProfileMyReview.adapter = historyAdapter

            txEditProfile.setOnClickListener {
                val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
                val action = MainFragmentDirections.actionMainFragmentToNaviProfileEdit()
                parentNavController.navigate(action)
            }
            btnSettings.setOnClickListener {
                val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
                val action = MainFragmentDirections.actionMainFragmentToNaviSetting()
                parentNavController.navigate(action)
            }

        }
    }
}