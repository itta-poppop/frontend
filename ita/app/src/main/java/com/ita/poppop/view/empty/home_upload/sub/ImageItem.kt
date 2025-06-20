package com.ita.poppop.view.empty.home_upload.sub

import android.net.Uri

// 어댑터에서 사용할 아이템 타입을 정의하는 sealed class
sealed class UploadItem {
    object Header : UploadItem()
    data class Image(val imageItem: ImageItem) : UploadItem()
}

// 기존 ImageItem 클래스는 그대로 유지
data class ImageItem(
    val id: Long,
    val uri: Uri
)