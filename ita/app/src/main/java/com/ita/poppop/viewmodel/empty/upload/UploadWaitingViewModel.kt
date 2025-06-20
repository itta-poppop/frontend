package com.ita.poppop.viewmodel.empty.upload

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.ita.poppop.view.empty.home_upload.sub.ImageItem
import com.ita.poppop.view.empty.home_upload.sub.UploadItem


class UploadWaitingViewModel: ViewModel() {


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



    private val _popupItem = MutableLiveData<String?>()
    val popupItem: LiveData<String?> get() = _popupItem

    // set 함수
    fun setPopupItem(item: String) {
        _popupItem.value = item
    }




    private val _waitingCount = MutableLiveData<Int?>()
    val waitingCount: LiveData<Int?> get() = _waitingCount

    // 양방향 바인딩을 위한 setter 함수
    fun setWaiting(count: Int?) {
        _waitingCount.value = count
    }


    val isAllValid = MediatorLiveData<Boolean>().apply {
        val validator = {
            value = waitingImage.value != null &&
                    waitingCount.value  != null &&
                    !popupItem.value.isNullOrBlank()
        }
        addSource(waitingImage) { validator() }
        addSource(popupItem) { validator() }
        addSource(waitingCount) { validator() }
    }
}