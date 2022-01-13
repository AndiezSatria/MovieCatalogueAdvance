package com.andiez.moviecatalogueadvance.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
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
                ShowType.TvShow -> {}
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
                            resource.data?.subList(
                                0,
                                10
                            )
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
                    binding.data =
                        resource.data?.let { DataMapper.mapDetailMovieDomainToPresenter(it) }
                }
            }
        }
    }
}