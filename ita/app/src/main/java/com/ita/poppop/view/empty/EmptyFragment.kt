package com.ita.poppop.view.empty

import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentEmptyBinding

class EmptyFragment: BaseFragment<FragmentEmptyBinding>(R.layout.fragment_empty) {
    override fun initView() {


        binding.apply {

            val args : EmptyFragmentArgs by navArgs()



            val navHostFragment = childFragmentManager
                .findFragmentById(R.id.fcv_empty_fragment_container) as NavHostFragment

            val navController = navHostFragment.navController

            when (args.type) {
                "search" -> {
                    val navGraph = navController.navInflater.inflate(R.navigation.navi_home_search)
                    navController.graph = navGraph
                }
                "notification" -> {
                    val navGraph = navController.navInflater.inflate(R.navigation.navi_home_notification)
                    navController.graph = navGraph
                }
                else -> {
                    val navGraph = navController.navInflater.inflate(R.navigation.navi_home_search)
                    navController.graph = navGraph
                }
            }

        }

    }
}