package com.ita.poppop.view.empty.upload

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ita.poppop.R


class UploadViewModel : ViewModel() {

    private val _uploadList = MutableLiveData<MutableList<UploadRVItem>>()
    val uploadList: LiveData<MutableList<UploadRVItem>> = _uploadList

    fun getUploadImg(){
        val list = mutableListOf<UploadRVItem>()
        /*list.clear()*/
        /*list.add(
            UploadRVItem(
                1,
                R.drawable.main_btn_favorites_icon,
                )
        )*/

        _uploadList.value = list
    }
}