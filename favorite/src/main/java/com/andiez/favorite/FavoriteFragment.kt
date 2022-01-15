package com.andiez.favorite

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andiez.favorite.databinding.FragmentFavoriteBinding
import com.andiez.favorite.favorite.DaggerFavoriteComponent
import com.andiez.favorite.favorite.FavoriteViewModel
import com.andiez.favorite.favorite.ViewModelFactory
import com.andiez.moviecatalogueadvance.MainActivity
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import com.andiez.moviecatalogueadvance.core.ui.MovieGridAdapter
import com.andiez.moviecatalogueadvance.core.utils.CommonUtils
import com.andiez.moviecatalogueadvance.core.utils.DataMapper
import com.andiez.moviecatalogueadvance.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.material.tabSelectionEvents
import javax.inject.Inject

class FavoriteFragment : Fragment(R.layout.fragment_favorite) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var movieAdapter: MovieGridAdapter
    private lateinit var tvShowAdapter: MovieGridAdapter
    private val movieClickListener: ((ShowItem) -> Unit) = { item ->
        val uri = Uri.parse(
            getString(
                com.andiez.moviecatalogueadvance.R.string.detail_args,
                CommonUtils.createDeeplinkArgs(item.id, ShowType.Movie)
            )
        )
        findNavController().navigate(uri)
    }
    private val tvClickListener: ((ShowItem) -> Unit) = { item ->
        val uri = Uri.parse(
            getString(
                com.andiez.moviecatalogueadvance.R.string.detail_args,
                CommonUtils.createDeeplinkArgs(item.id, ShowType.TvShow)
            )
        )
        findNavController().navigate(uri)
    }

    override fun onAttach(context: Context) {
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFavoriteBinding.bind(view)
        (requireActivity() as MainActivity).setBottomNavVisibility(true)
        (requireActivity() as MainActivity).supportActionBar?.show()

        movieAdapter = MovieGridAdapter()
        tvShowAdapter = MovieGridAdapter()
        movieAdapter.onItemClick = movieClickListener
        tvShowAdapter.onItemClick = tvClickListener

        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            tabShow
                .tabSelectionEvents()
                .onEach {
                    binding.ivNoData.visibility = View.GONE
                    binding.tvNoData.visibility = View.GONE
                    when (it.tab.position) {
                        0 -> {
                            rvShow.adapter = movieAdapter
                            observeMovies()
                        }
                        1 -> {
                            rvShow.adapter = tvShowAdapter
                            observeTvShows()
                        }
                    }
                }.launchIn(lifecycleScope)
        }
    }

    private fun observeMovies() {
        favoriteViewModel.movies.observe(viewLifecycleOwner) { movies ->
            movies?.let {
                if (it.isNotEmpty()) {
                    binding.pbShow.visibility = View.GONE
                    movieAdapter.submitList(DataMapper.mapMovieDomainsToPresenters(it))
                } else {
                    binding.ivNoData.visibility = View.VISIBLE
                    binding.tvNoData.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeTvShows() {
        favoriteViewModel.tvShows.observe(viewLifecycleOwner) { tv ->
            tv?.let {
                if (it.isNotEmpty()) {
                    binding.pbShow.visibility = View.GONE
                    tvShowAdapter.submitList(DataMapper.mapTvDomainsToPresenters(it))
                } else {
                    binding.ivNoData.visibility = View.VISIBLE
                    binding.tvNoData.visibility = View.VISIBLE
                }
            }
        }
    }
}