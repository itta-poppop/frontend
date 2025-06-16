package com.ita.poppop.view.main.favorites

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentFavoritesBinding
import com.ita.poppop.databinding.ToastMessageBinding
import com.ita.poppop.util.SwipeHelper
import com.ita.poppop.view.main.MainFragmentDirections
import com.ita.poppop.view.main.hide
import com.ita.poppop.view.main.home.InfoFragment
import com.ita.poppop.view.main.show

class FavoritesFragment: BaseFragment<FragmentFavoritesBinding>(R.layout.fragment_favorites) {
    private lateinit var favoritesViewModel: FavoritesViewModel

    private val favoritesRVAdapter by lazy {
        FavoritesRVAdapter()
    }

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun initView() {
        binding.apply {
            favoritesViewModel = ViewModelProvider(this@FavoritesFragment).get(FavoritesViewModel::class.java)
            linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


            binding.rvFavorites.apply {
                layoutManager = linearLayoutManager
                adapter = favoritesRVAdapter

                //val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                //addItemDecoration(dividerItemDecoration)

                val itemTouchHelper = ItemTouchHelper(SwipeHelper())
                itemTouchHelper.attachToRecyclerView(this)
                itemAnimator = null
            }

            favoritesViewModel.getFavorites()
            favoritesViewModel.favoritesList.observe(viewLifecycleOwner, Observer { response ->
                favoritesRVAdapter.submitList(response)

                binding.emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })

            favoritesRVAdapter.setFavoritesItemClickListener(object : FavoritesRVAdapter.FavoritesItemClickListener{
                override fun onItemClick(position: Int) {
                    val parentNavController = requireActivity().findNavController(R.id.fcv_main_activity_container)
                    val action = MainFragmentDirections.actionMainFragmentToNaviInfo()
                    parentNavController.navigate(action)
                }

                override fun onDeleteClick(position: Int) {
                    favoritesViewModel.deleteFavorites(position)

                    // toast message 띄우기
                    val removedItem = favoritesRVAdapter.currentList.getOrNull(position)?.title
                    val toastBinding = ToastMessageBinding.inflate(layoutInflater)
                    toastBinding.tvToastMessage.text = "${removedItem}이(가) 즐겨찾기에 삭제되었습니다."

                    Toast(requireContext()).apply {
                        duration = Toast.LENGTH_LONG
                        setGravity(Gravity.NO_GRAVITY, 0, 600)
                        view = toastBinding.root
                        show()
                    }
                }
            })
        }
    }

}