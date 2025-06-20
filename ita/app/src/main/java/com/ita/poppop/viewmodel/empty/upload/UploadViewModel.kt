package com.ita.poppop.viewmodel.empty.upload

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.ita.poppop.view.empty.home_upload.sub.ImageItem
import com.ita.poppop.view.empty.home_upload.sub.UploadItem

class UploadViewModel: ViewModel() {
    companion object {
        private const val MAX_IMAGE_COUNT = 5 // 실제 이미지 최대 개수
        private const val MAX_UPLOAD_COUNT = MAX_IMAGE_COUNT + 1 // 헤더 포함 최대 개수 (6개)
    }

    // 실제 이미지 데이터만 관리
    private val _imageList = MutableLiveData<List<ImageItem>>(emptyList())
    val imageList: LiveData<List<ImageItem>> get() = _imageList
    // 어댑터에서 사용할 UploadItem 리스트 (헤더 + 이미지들)
    val uploadList: LiveData<List<UploadItem>> = _imageList.map { imageList ->
        buildList {
            // 항상 헤더를 맨 앞에 추가
            add(UploadItem.Header)
            // 이미지들을 UploadItem.Image로 변환해서 추가
            addAll(imageList.map { UploadItem.Image(it) })
        }
    }

    fun addItem(item: ImageItem) {
        val currentList = _imageList.value?.toMutableList() ?: mutableListOf()
        if (currentList.size < MAX_IMAGE_COUNT) {
            currentList.add(item)
            _imageList.value = currentList
            Log.d("checkList", "Added item: ${item.id}, total: ${currentList.size}")
        } else {
            Log.d("checkList", "Cannot add more items. Maximum limit reached.")
        }
    }

    fun updateItem(index: Int, newItem: ImageItem) {
        val currentList = _imageList.value?.toMutableList() ?: return
        if (index < currentList.size) {
            currentList[index] = newItem
            _imageList.value = currentList
        }
    }

    fun removeItem(removeId: Long) {
        val filteredList = _imageList.value?.filterNot { it.id == removeId }
        _imageList.value = filteredList
    }

    // 디버깅용 - 현재 이미지 개수 확인
    fun getImageCount(): Int = _imageList.value?.size ?: 0

    // 추가로 넣을 수 있는 개수를 반환하는 함수
    fun getRemainingSlots(): Int {
        val currentImageCount = _imageList.value?.size ?: 0
        return (MAX_IMAGE_COUNT - currentImageCount).coerceAtLeast(0)
    }

    // 최대 개수에 도달했는지 확인하는 함수 (선택사항)
    fun isMaxCapacityReached(): Boolean {
        return getRemainingSlots() == 0
    }

    // 현재 전체 업로드 리스트 개수 확인 (헤더 포함 - 최대 6개)
    fun getTotalUploadCount(): Int {
        return (_imageList.value?.size ?: 0) + 1 // +1 for header
    }


    private val _popupItem = MutableLiveData<String?>()
    val popupItem: LiveData<String?> get() = _popupItem

    // set 함수
    fun setPopupItem(item: String) {
        _popupItem.value = item
    }



    private val _reviewContent = MutableLiveData<String?>()
    val reviewContent: LiveData<String?> get() = _reviewContent

    // 양방향 바인딩을 위한 setter 함수
    fun setReviewContent(content: String?) {
        _reviewContent.value = content
    }


    // 텍스트 변경 감지 함수 (실시간 업데이트용)
    fun onReviewTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        setReviewContent(s?.toString())
    }



    val isAllValid = MediatorLiveData<Boolean>().apply {
        val validator = {
            value = !imageList.value.isNullOrEmpty() &&
                    !popupItem.value.isNullOrBlank() &&
                    !reviewContent.value.isNullOrBlank()
        }
        addSource(imageList) { validator() }
        addSource(popupItem) { validator() }
        addSource(reviewContent) { validator() }
    }


    private val _waitingImage = MutableLiveData<ImageItem?>()
    val waitingImage: LiveData<ImageItem?> get() = _waitingImage

    // set 함수
    fun setWaitingImage(item: ImageItem) {
        _waitingImage.value = item
    }

    // get 함수
    fun getWaitingImage(): ImageItem? {
        return _waitingImage.value
    }

    // remove 함수 (null로 초기화)
    fun removeWaitingImage() {
        _waitingImage.value = null
    }


}