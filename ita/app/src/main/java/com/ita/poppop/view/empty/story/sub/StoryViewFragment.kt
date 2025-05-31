package com.ita.poppop.view.empty.story.sub

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentStoryViewBinding
import com.ita.poppop.util.bottomsheet.WaitingBottomSheet
import com.ita.poppop.view.empty.story.StoryFragment

class StoryViewFragment : BaseFragment<FragmentStoryViewBinding>(R.layout.fragment_story_view) {

    companion object {
        private const val ARG_DATA = "data"
        private const val DURATION = 15_000L
        private const val INTERVAL = 10L
        private const val MAX_PROGRESS = 1000

        fun newInstance(data: String): StoryViewFragment {
            return StoryViewFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DATA, data)
                }
            }
        }

    }

    private var data: String? = null
    private var progressTimer: CountDownTimer? = null

    override fun initView() {
        loadArgs()
        initListeners()
        binding.tvStoryUser.text = data
    }

    private fun loadArgs() {
        data = arguments?.getString(ARG_DATA)
    }

    private fun initListeners() = binding.run {
        ibStoryCancel.setOnClickListener {
            if (childFragmentManager.backStackEntryCount > 0) {
                childFragmentManager.popBackStack()
            } else {
                findNavController(requireParentFragment()).popBackStack()
            }
        }

        ibStory3dot.setOnClickListener {
            cancelProgressTimer()
            WaitingBottomSheet().show(parentFragmentManager, "WaitingBottomSheet")
        }
    }

    private fun startProgressTimer() {
        progressTimer?.cancel()

        progressTimer = object : CountDownTimer(DURATION, INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                val progress = ((DURATION - millisUntilFinished) * MAX_PROGRESS / DURATION).toInt()
                binding.pb.progress = progress
            }

            override fun onFinish() {
                binding.pb.progress = MAX_PROGRESS
                Log.d("ProgressBar", "Timer finished")
                (parentFragment as? StoryFragment)?.changeViewPager()
            }
        }.also {
            it.start()
        }
    }

    private fun cancelProgressTimer() {
        progressTimer?.cancel()
        binding.pb.progress = 0
        progressTimer = null
    }

    override fun onResume() {
        super.onResume()
        startProgressTimer()
    }

    override fun onPause() {
        super.onPause()
        cancelProgressTimer()
    }
}
