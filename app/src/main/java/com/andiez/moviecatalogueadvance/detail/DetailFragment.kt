package com.andiez.moviecatalogueadvance.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.andiez.moviecatalogueadvance.MainActivity
import com.andiez.moviecatalogueadvance.R
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import com.andiez.moviecatalogueadvance.core.ui.CastListAdapter
import com.andiez.moviecatalogueadvance.core.utils.DataMapper
import com.andiez.moviecatalogueadvance.databinding.FragmentDetailBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var castAdapter: CastListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)
        castAdapter = CastListAdapter()
        viewModel.setIdAndShowType(args.idType)

        binding.rvCast.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvCast.setHasFixedSize(true)
        binding.rvCast.adapter = castAdapter

        (requireActivity() as MainActivity).setBottomNavVisibility(false)
        (requireActivity() as MainActivity).supportActionBar?.hide()
        viewModel.showType.observe(viewLifecycleOwner) { type ->
            when (type) {
                ShowType.Movie -> observeMovieDetail()
                ShowType.TvShow -> observeTvShowDetail()
                else -> {}
            }
        }

        viewModel.casts.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.pbDetail.visibility = View.GONE
                    resource.message?.let {
                        Snackbar.make(
                            binding.root,
                            it, Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    findNavController().popBackStack()
                }
                is Resource.Loading -> {
                    binding.pbDetail.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbDetail.visibility = View.GONE
                    castAdapter.submitList(
                        DataMapper.mapCastDomainsToPresenters(
                            resource.data?.let {
                                it.subList(
                                    0,
                                    if (it.size < 10) (it.size - 1) else 10
                                )
                            }
                        )
                    )
                }
            }
        }
    }

    private fun observeMovieDetail() {
        viewModel.movieDetail.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.pbDetail.visibility = View.GONE
                    resource.message?.let {
                        Snackbar.make(
                            binding.root,
                            it, Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    findNavController().popBackStack()
                }
                is Resource.Loading -> {
                    binding.pbDetail.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbDetail.visibility = View.GONE
                    binding.fabFavorite.isClickable = true
                    resource.data?.let { detail ->
                        binding.fabFavorite.setOnClickListener {
                            setMovieFavorite(detail.id, !detail.isFavorite)
                        }
                        binding.data =
                            DataMapper.mapDetailMovieDomainToPresenter(detail)
                        setFavoriteButtonState(detail.isFavorite)
                    }
                }
            }
        }
    }


    private fun observeTvShowDetail() {
        viewModel.tvShowDetail.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Error -> {
                    binding.pbDetail.visibility = View.GONE
                    resource.message?.let {
                        Snackbar.make(
                            binding.root,
                            it, Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    findNavController().popBackStack()
                }
                is Resource.Loading -> {
                    binding.pbDetail.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    binding.pbDetail.visibility = View.GONE
                    binding.fabFavorite.isClickable = true
                    resource.data?.let { detail ->
                        binding.fabFavorite.setOnClickListener {
                            setTvFavorite(detail.id, !detail.isFavorite)
                        }
                        binding.data =
                            DataMapper.mapDetailTvShowDomainToPresenter(detail)
                        setFavoriteButtonState(detail.isFavorite)
                    }
                }
            }
        }
    }

    private fun setMovieFavorite(id: Int, state: Boolean) {
        /*
        update db isFavorite
         */
        viewModel.setMovieFavorite(id, state)
    }

    private fun setTvFavorite(id: Int, state: Boolean) {
        /*
        update db isFavorite
         */
        viewModel.setTvFavorite(id, state)
    }


    private fun setFavoriteButtonState(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fabFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_favorite_border
                )
            )
        }
    }
}