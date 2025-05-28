package com.ita.poppop.view.empty.editprofile

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentEditProfileBinding
import com.ita.poppop.util.bottomsheet.UploadProfileBottomSheet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>(R.layout.fragment_edit_profile) {

    override fun initView() {
        setupWindowInsets()
        setupToolbar()
        setupProfileEditButton()
    }

    private fun setupToolbar() {
        binding.mtEditProfile.apply {
            setNavigationIcon(R.drawable.chevron_left)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }

    private fun setupProfileEditButton() {
        binding.mcvProfileEdit.setOnClickListener {
            showProfileUploadBottomSheet()
        }
    }

    private fun showProfileUploadBottomSheet() {
        UploadProfileBottomSheet().show(
            parentFragmentManager,
            PROFILE_SETTING_DIALOG_TAG
        )
    }

    companion object {
        private const val PROFILE_SETTING_DIALOG_TAG = "profile_setting_dialog"
    }



}
