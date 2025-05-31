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
import com.ita.poppop.databinding.FragmentUploadBottomSheetBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UploadBottomSheet : BottomSheetDialogFragment() {

    private var _binding: FragmentUploadBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var photoFile: File? = null
    private var photoUri: Uri? = null

    private val requestGalleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        // TODO: 갤러리에서 선택한 이미지 처리
    }

    private val requestCameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == android.app.Activity.RESULT_OK) {
            // TODO: 사진 촬영 결과 처리
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.clOpenGalleryArea.setOnClickListener { openGallery() }
        binding.clTakeCameraArea.setOnClickListener { openCamera() }
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
            type = "image/*"
        }
        requestGalleryLauncher.launch(intent)
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val canResolve = intent.resolveActivity(requireActivity().packageManager) != null

        if (canResolve) {
            try {
                val file = createImageFile()
                photoFile = file
                photoUri = FileProvider.getUriForFile(
                    requireContext(),
                    "com.ita.poppop.fileprovider",
                    file
                )
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                requestCameraLauncher.launch(intent)
            } catch (e: Exception) {
                Log.e(TAG, "카메라 파일 생성 실패", e)
            }
        } else {
            Log.e(TAG, "카메라 앱이 없습니다")
            // TODO: 사용자에게 카메라 앱 없음 알림
        }
    }

    private fun createImageFile(): File {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "UploadBottomSheet"
    }
}
