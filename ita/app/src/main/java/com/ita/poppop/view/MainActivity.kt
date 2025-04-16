package com.ita.poppop.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.ita.poppop.R
import com.ita.poppop.base.BaseActivity
import com.ita.poppop.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.apply {
            // FragmentContainerView에 동적으로 navi 연결
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fcv_main_activity_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.setGraph(R.navigation.navi_launch)


        }
    }
}