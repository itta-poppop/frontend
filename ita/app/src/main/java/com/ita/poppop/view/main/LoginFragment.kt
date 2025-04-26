package com.ita.poppop.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentLoginBinding
import com.ita.poppop.databinding.FragmentMainBinding

class LoginFragment: BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun initView() {
        binding.apply {
            btLoginKakao.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }

        }

    }
}