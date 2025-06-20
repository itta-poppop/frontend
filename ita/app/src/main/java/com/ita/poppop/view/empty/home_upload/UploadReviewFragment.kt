package com.ita.poppop.view.empty.home_upload

import android.graphics.Color
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.os.BundleCompat
import androidx.databinding.adapters.ViewBindingAdapter.setClickListener
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentUploadReviewBinding
import com.ita.poppop.util.bottomsheet.UploadBottomSheet
import com.ita.poppop.view.empty.home_upload.sub.ImageItem
import com.ita.poppop.view.empty.home_upload.sub.UploadImageAdapter
import com.ita.poppop.view.empty.home_upload.sub.UploadImageItemDecoration
import com.ita.poppop.viewmodel.empty.upload.UploadViewModel
import kotlin.math.absoluteValue

class UploadReviewFragment : BaseFragment<FragmentUploadReviewBinding>(R.layout.fragment_upload_review) {
    private lateinit var uploadImageAdapter: UploadImageAdapter
    private lateinit var uploadViewModel: UploadViewModel

    override fun initView() {
        setupWindowInsets()
        setupToolbar()
        setupUploadRecycler()
        setFragmentResult()
        setClickListener()
        setViewModel()
    }


    private fun setViewModel() {
        uploadViewModel = ViewModelProvider(this)[UploadViewModel::class.java]
        // 바인딩에 ViewModel 연결
        binding.uploadReviewViewModel = uploadViewModel

        // UploadItem 리스트를 관찰 (헤더가 포함된 리스트)
        uploadViewModel.uploadList.observe(viewLifecycleOwner) { uploadItemList ->
            uploadImageAdapter.submitList(uploadItemList)
        }

        uploadViewModel.isAllValid.observe(viewLifecycleOwner) { valid ->
            Log.d("checkViewModel","imageList : ${uploadViewModel.imageList.value}")
            Log.d("checkViewModel","popupItem : ${uploadViewModel.popupItem.value}")
            Log.d("checkViewModel","reviewContent : ${uploadViewModel.reviewContent.value}")
            binding.btUploadReview.isEnabled = valid

        }
    }


    private fun setFragmentResult() {
        parentFragmentManager.setFragmentResultListener("upload_result", this) { _, bundle ->
            val uriList = BundleCompat.getParcelableArrayList(bundle, "images", Uri::class.java)
            uriList?.let { uris ->
                addImages(uris)
            }
        }
    }

    private fun setupUploadRecycler() = with(binding.rvUploadReview) {
        uploadImageAdapter = UploadImageAdapter(
            onAddClick = {
                Log.d("checkList", "Add button clicked")
                showUploadBottomSheet()
            },
            onDeleteClick = { id ->
                Log.d("checkList", "Delete clicked for id: $id")
                deleteImage(id)
            }
        )
        adapter = uploadImageAdapter
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        addItemDecoration(UploadImageItemDecoration())
    }

    private fun addImages(uris: List<Uri>) {
        uris.forEach { uri ->
            val imageItem = ImageItem(
                id = uri.toString().hashCode().toLong().absoluteValue,
                uri = uri
            )
            uploadViewModel.addItem(imageItem)
            Log.d("checkList", "Adding image with id: ${imageItem.id}")
        }
    }

    private fun deleteImage(id: Long) {
        Log.d("checkList", "Deleting image with id: $id")
        uploadViewModel.removeItem(id)
    }

    private fun setClickListener() {
        binding.mcvSearchArea.setOnClickListener {
            navigateTo(UploadReviewFragmentDirections.actionUploadReviewFragmentToSearchFragment())
        }
    }

    private fun setupToolbar() {
        binding.mtUploadReview.apply {
            setNavigationIcon(R.drawable.icon_x_close_b)
            setNavigationOnClickListener { handleBackNavigation() }
        }
    }

    private fun navigateTo(action: NavDirections) {
        val navController = requireActivity().findNavController(R.id.fcv_main_activity_container)
        navController.navigate(action)
    }

    private fun showUploadBottomSheet() {
        if(uploadViewModel.getRemainingSlots() <= 0){
            view?.let { Snackbar.make(it, "이미지는 최대 5장까지 첨부할 수 있습니다.", Snackbar.LENGTH_SHORT).show() }
        }else{
            UploadBottomSheet(uploadViewModel.getRemainingSlots()).show(parentFragmentManager, "upload_sheet")
        }
    }
}