package com.ita.poppop.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.fragment.NavHostFragment
import com.ita.poppop.R
import com.ita.poppop.base.BaseActivity
import com.ita.poppop.databinding.ActivityMainBinding


class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {

            //내비게이션 바(Navigation Bar)의 명암 대비 설정을 비활성화 -> 투명화
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                window.isNavigationBarContrastEnforced = false

            }

            // FragmentContainerView에 동적으로 navi 연결
            val navHostFragment = supportFragmentManager
                .findFragmentById(R.id.fcv_main_activity_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.setGraph(R.navigation.navi_launch)

        }
    }
}