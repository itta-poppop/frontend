package com.ita.poppop.view.empty.editprofile

import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentEditProfileBinding
import com.ita.poppop.util.bottomsheet.UploadBottomSheet
import com.ita.poppop.util.bottomsheet.UploadProfileBottomSheet


class EditProfileFragment: BaseFragment<FragmentEditProfileBinding>(R.layout.fragment_edit_profile) {
    override fun initView() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBarInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                systemBarInsets.bottom // 하단 패딩만 적용
            )
            insets
        }
        binding.apply {
            mtEditProfile.setNavigationIcon(R.drawable.chevron_left)
            mtEditProfile.setNavigationOnClickListener {

//                 findNavController(requireParentFragment()).popBackStack() // Navigation Component 사용 시
            }




            mcvProfileEdit.setOnClickListener {
                val customDialogFragment = UploadProfileBottomSheet()
                customDialogFragment.show(parentFragmentManager, "sss")
            }


        }

    }
}