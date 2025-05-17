package com.ita.poppop.view.empty.story

import android.os.CountDownTimer
import android.util.Log
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentStoryBinding


class StoryFragment: BaseFragment<FragmentStoryBinding>(R.layout.fragment_story) {
    private val duration = 10_000L  // 30초
    private val interval = 10L      // 30ms마다 업데이트

    override fun initView() {
        binding.apply {
            object : CountDownTimer(duration, interval) {
                override fun onTick(millisUntilFinished: Long) {
                    val progress = ((duration - millisUntilFinished) * 1000 / duration).toInt()
                    pb.progress = progress
                }

                override fun onFinish() {
                    pb.progress = 1000
                    Log.d("ProgressBar", "30초 완료됨")
                }
            }.start()
        }
    }
}