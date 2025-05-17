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

    // 뷰바인딩 객체 선언
    private var _binding: FragmentUploadBottomSheetBinding? = null
    // 유효한 바인딩 객체를 반환하는 getter 속성
    private val binding get() = _binding!!

    private lateinit var photoFile: File
    private lateinit var photoURI: Uri
    // requestGalleryLauncher
    private val requestGalleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        try {

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // requestGalleryLauncher
    private val requestCameraFileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
//            val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
//            val action = MainFragmentDirections.actionMainFragmentToNaviPhoto(
//                photoPath = photoFile.absolutePath,
//                photoUri = photoURI
//            )
//            parentNavController.navigate(action)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 뷰바인딩 초기화
        _binding = FragmentUploadBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clOpenGalleryArea.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }
        binding.clTakeCameraArea.setOnClickListener {
            dispatchTakePictureIntent()
            Log.d("checkPhoto","photo 실행")
        }



    }
    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // 카메라 앱이 있는지 확인
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            // 사진 파일 생성
            photoFile = createImageFile()

            photoFile.also {
                photoURI = FileProvider.getUriForFile(
                    requireContext(),                        
                    "com.ita.poppop.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                requestCameraFileLauncher.launch(takePictureIntent)
            }
        }
    }

    private fun createImageFile(): File {
        // 타임스탬프로 파일 이름 생성
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir = requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "JPEG_${timeStamp}_",
            ".jpg",
            storageDir
        )
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // 메모리 누수 방지를 위해 바인딩 객체 해제
        _binding = null
    }
}

