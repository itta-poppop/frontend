package com.ita.poppop.view.empty.photo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.ImageView
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentPhotoBinding

class PhotoFragment: BaseFragment<FragmentPhotoBinding>(R.layout.fragment_photo) {

    private var photoPath: String? = null
    private var photoUri: Uri? = null


    override fun initView() {
        // 전달받은 사진 경로와 URI
        photoPath = arguments?.getString("photoPath")
        photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("imageUri", Uri::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("imageUri")
        }
        Log.d("checkBitmap","photoPath : ${photoPath}, photoUri : ${photoUri}")
        // 이미지 표시


        photoPath?.let {
            // 원본 이미지 크기를 유지하기 위해 옵션 설정
            val options = BitmapFactory.Options().apply {
                inJustDecodeBounds = false  // 실제 이미지 로드
                inSampleSize = 1            // 원본 크기 유지 (축소 없음)
                inPreferredConfig = Bitmap.Config.ARGB_8888  // 고품질 이미지
                inScaled = false            // 이미지 스케일링 하지 않음
            }

            // 이미지 디코딩
            val bitmap = BitmapFactory.decodeFile(it, options)

            // EXIF 메타데이터를 읽어서 이미지 방향 확인
            val exif = ExifInterface(it)
            val orientation = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_UNDEFINED
            )

            // 이미지 방향에 따라 회전 처리
            val rotatedBitmap = when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> rotateImage(bitmap, 90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> rotateImage(bitmap, 180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> rotateImage(bitmap, 270f)
                else -> bitmap
            }

            // ImageView에 회전된 비트맵 설정
            binding.imagePreview.apply {
                adjustViewBounds = true     // 이미지 비율 유지
                scaleType = ImageView.ScaleType.FIT_CENTER  // 이미지 중앙 정렬, 비율 유지
                setImageBitmap(rotatedBitmap)
            }
        }

        binding.apply {

        }
    }
    // 이미지 회전을 위한 헬퍼 함수
    private fun rotateImage(source: Bitmap, angle: Float): Bitmap {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }
}

