package com.ita.poppop.util.bottomsheet

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.ita.poppop.databinding.FragmentUploadProfileBottomSheetBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class UploadProfileBottomSheet : BottomSheetDialogFragment() {

    // 뷰바인딩 객체 선언
    private var _binding: FragmentUploadProfileBottomSheetBinding? = null
    // 유효한 바인딩 객체를 반환하는 getter 속성
    private val binding get() = _binding!!
    // requestGalleryLauncher
    private val requestGalleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        try {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 뷰바인딩 초기화
        _binding = FragmentUploadProfileBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clGalleryArea.setOnClickListener {

        }
        binding.clGalleryArea.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 메모리 누수 방지를 위해 바인딩 객체 해제
        _binding = null
    }
}
