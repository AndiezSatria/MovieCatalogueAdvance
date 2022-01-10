package com.andiez.moviecatalogueadvance.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andiez.moviecatalogueadvance.MainActivity
import com.andiez.moviecatalogueadvance.R
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem
import com.andiez.moviecatalogueadvance.core.ui.MovieGridAdapter
import com.andiez.moviecatalogueadvance.core.utils.DataMapper
import com.andiez.moviecatalogueadvance.core.utils.MovieViewHolder
import com.andiez.moviecatalogueadvance.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var movieAdapter: MovieGridAdapter
    private lateinit var tvShowAdapter: MovieGridAdapter
    private lateinit var popularAdapter: MovieGridAdapter
    private val movieClickListener: ((ShowItem) -> Unit) = { item ->
        // TODO: Navigation to detail
    }
    private val tvClickListener: ((ShowItem) -> Unit) = { item ->
        // TODO: Navigation to detail
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        (requireActivity() as MainActivity).supportActionBar?.setTitle(R.string.text_home)
        movieAdapter = MovieGridAdapter()
        popularAdapter = MovieGridAdapter().apply { viewHolder = MovieViewHolder.Popular }
        tvShowAdapter = MovieGridAdapter()
        
        movieAdapter.onItemClick = movieClickListener
        popularAdapter.onItemClick = movieClickListener
        tvShowAdapter.onItemClick = tvClickListener

        with(binding) {
            lifecycleOwner = viewLifecycleOwner
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvPopularMovie.setHasFixedSize(true)
            rvPopularMovie.layoutManager = layoutManager
            rvPopularMovie.adapter = popularAdapter
            rvShow.adapter = movieAdapter
            observeMovies()

            tabShow.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when (tab?.position) {
                        0 -> {
                            rvShow.adapter = movieAdapter
                            observeMovies()
                        }
                        1 -> {
                            rvShow.adapter = tvShowAdapter
                            observeTvShows()
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }

        viewModel.getPopularMovies().observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    val snackbar = Snackbar.make(
                        binding.root,
                        response.message.toString(),
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show()
                    binding.pbPopularMovie.visibility = View.GONE
                }
                is Resource.Loading -> binding.pbPopularMovie.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.pbPopularMovie.visibility = View.GONE
                    popularAdapter.submitList(DataMapper.mapMovieDomainsToPresenters(response.data))
                }
            }
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
                    binding.pbShow.visibility = View.GONE
                    movieAdapter.submitList(DataMapper.mapMovieDomainsToPresenters(response.data))
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
                    binding.pbShow.visibility = View.GONE
                    tvShowAdapter.submitList(DataMapper.mapTvDomainsToPresenters(response.data))
                }
            }
        }
    }
}