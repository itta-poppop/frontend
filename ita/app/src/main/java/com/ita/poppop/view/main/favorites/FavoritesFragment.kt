package com.ita.poppop.view.main.favorites

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ita.poppop.R
import com.ita.poppop.base.BaseFragment
import com.ita.poppop.databinding.FragmentFavoritesBinding
import com.ita.poppop.view.main.hide
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

                val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
                addItemDecoration(dividerItemDecoration)
            }

            favoritesViewModel.getFavorites()
            favoritesViewModel.favoritesList.observe(viewLifecycleOwner, Observer { response ->
                favoritesRVAdapter.submitList(response)

                binding.emptyStateLayout.root.run { if(response.isNullOrEmpty()) show() else hide()}
            })
        }
    }

}