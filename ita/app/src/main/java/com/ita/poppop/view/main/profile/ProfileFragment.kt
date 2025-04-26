package com.ita.poppop.view.main.profile

import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentHomeBinding
import com.ita.poppop.databinding.FragmentProfileBinding
import com.ita.poppop.view.main.profile.holder.ProfileReviewAdapter
import com.ita.poppop.view.main.profile.holder.ProfileReviewItemDecoration

class ProfileFragment: BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    override fun initView() {
        binding.apply {

            val myList = mutableListOf(0,1,2,3,4,5)

            val historyAdapter = ProfileReviewAdapter(myList)
            val spacingInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                11f,   // dp 값
                context?.resources?.displayMetrics
            ).toInt()

            rvProfileMyReview.addItemDecoration(ProfileReviewItemDecoration(spacingInPx))
            rvProfileMyReview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvProfileMyReview.adapter = historyAdapter

        }
    }
}