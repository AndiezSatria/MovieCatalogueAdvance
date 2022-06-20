package com.andiez.moviecatalogueadvance.home

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.andiez.moviecatalogueadvance.MainActivity
import com.andiez.moviecatalogueadvance.R
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import com.andiez.moviecatalogueadvance.core.ui.MovieGridAdapter
import com.andiez.moviecatalogueadvance.core.utils.DataMapper
import com.andiez.moviecatalogueadvance.core.utils.CommonUtils
import com.andiez.moviecatalogueadvance.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.material.tabSelectionEvents

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieAdapter: MovieGridAdapter
    private lateinit var tvShowAdapter: MovieGridAdapter
    private val movieClickListener: ((ShowItem) -> Unit) = { item ->
        val uri = Uri.parse(
            getString(
                R.string.detail_args,
                CommonUtils.createDeeplinkArgs(item.id, ShowType.Movie)
            )
        )
        findNavController().navigate(uri)
    }
    private val tvClickListener: ((ShowItem) -> Unit) = { item ->
        val uri = Uri.parse(
            getString(
                R.string.detail_args,
                CommonUtils.createDeeplinkArgs(item.id, ShowType.TvShow)
            )
        )
        findNavController().navigate(uri)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        (requireActivity() as MainActivity).setBottomNavVisibility(true)
        (requireActivity() as MainActivity).supportActionBar?.show()
        (requireActivity() as MainActivity).supportActionBar?.setTitle(R.string.text_home)

        movieAdapter = MovieGridAdapter()
        tvShowAdapter = MovieGridAdapter()

        movieAdapter.onItemClick = movieClickListener
        tvShowAdapter.onItemClick = tvClickListener

        with(binding) {
            lifecycleOwner = viewLifecycleOwner

            tabShow
                .tabSelectionEvents()
                .onEach {
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
        viewModel.getMovies().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    val snackbar = Snackbar.make(
                        binding.root,
                        response.message.toString(),
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show()
                    binding.pbShow.visibility = View.GONE
                }
                is Resource.Loading -> binding.pbShow.visibility = View.VISIBLE
                is Resource.Success -> {
                    response.data?.let {
                        if (it.isNotEmpty()) {
                            binding.pbShow.visibility = View.GONE
                            movieAdapter.submitList(
                                DataMapper.mapMovieDomainsToPresenters(
                                    it
                                )
                            )
                        } else {
                            binding.ivNoData.visibility = View.VISIBLE
                            binding.tvNoData.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun observeTvShows() {
        viewModel.getTvShows().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    val snackbar = Snackbar.make(
                        binding.root,
                        response.message.toString(),
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show()
                    binding.pbShow.visibility = View.GONE
                }
                is Resource.Loading -> binding.pbShow.visibility = View.VISIBLE
                is Resource.Success -> {
                    response.data?.let {
                        if (it.isNotEmpty()) {
                            binding.pbShow.visibility = View.GONE
                            tvShowAdapter.submitList(
                                DataMapper.mapTvDomainsToPresenters(
                                    it
                                )
                            )
                        } else {
                            binding.ivNoData.visibility = View.VISIBLE
                            binding.tvNoData.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }
}