package com.ita.poppop.view.main

import androidx.navigation.fragment.findNavController
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    override fun initView() {
        binding.apply {
            btLoginKakao.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            }

        }

    }
}