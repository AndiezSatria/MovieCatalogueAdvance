package com.andiez.search

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.andiez.moviecatalogueadvance.MainActivity
import com.andiez.moviecatalogueadvance.core.data.Resource
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowItem
import com.andiez.moviecatalogueadvance.core.presenter.model.ShowType
import com.andiez.moviecatalogueadvance.core.ui.MovieGridAdapter
import com.andiez.moviecatalogueadvance.core.utils.CommonUtils
import com.andiez.moviecatalogueadvance.core.utils.DataMapper
import com.andiez.moviecatalogueadvance.di.UseCaseModuleDependencies
import com.andiez.search.databinding.FragmentSearchBinding
import com.andiez.search.search.DaggerSearchComponent
import com.andiez.search.search.SearchViewModel
import com.andiez.search.search.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import reactivecircus.flowbinding.material.tabSelectionEvents
import javax.inject.Inject

@FlowPreview
@ExperimentalCoroutinesApi
class SearchFragment : Fragment(R.layout.fragment_search) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: SearchViewModel by viewModels { factory }

    private lateinit var movieAdapter: MovieGridAdapter
    private lateinit var tvShowAdapter: MovieGridAdapter
    private val movieClickListener: ((ShowItem) -> Unit) = { item ->
        val uri = Uri.parse(
            getString(
                com.andiez.moviecatalogueadvance.R.string.detail_args,
                CommonUtils.createDeeplinkArgs(item.id, ShowType.Movie, true)
            )
        )
        findNavController().navigate(uri)
    }
    private val tvClickListener: ((ShowItem) -> Unit) = { item ->
        val uri = Uri.parse(
            getString(
                com.andiez.moviecatalogueadvance.R.string.detail_args,
                CommonUtils.createDeeplinkArgs(item.id, ShowType.TvShow, true)
            )
        )
        findNavController().navigate(uri)
    }

    override fun onAttach(context: Context) {
        DaggerSearchComponent
            .builder()
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().applicationContext,
                    UseCaseModuleDependencies::class.java
                )
            )
            .context(context)
            .build()
            .inject(this)
        super.onAttach(context)
    }

    private lateinit var binding: FragmentSearchBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        showNoData(true)
        (requireActivity() as MainActivity).setBottomNavVisibility(true)
        (requireActivity() as MainActivity).supportActionBar?.show()

        movieAdapter = MovieGridAdapter()
        tvShowAdapter = MovieGridAdapter()
        movieAdapter.onItemClick = movieClickListener
        tvShowAdapter.onItemClick = tvClickListener

        observeMovieSearch()
        observeTvSearch()
        with(binding) {
            inputSearch.editText?.addTextChangedListener {
                showNoData(false)
                showNotFound(false)
                viewModel.setQuery(it.toString())
                if (it.toString().trim() == "") {
                    showNoData(true)
                    movieAdapter.submitList(emptyList())
                    tvShowAdapter.submitList(emptyList())
                }
            }
            tabShow.tabSelectionEvents()
                .onEach {
                    when (it.tab.position) {
                        0 -> {
                            rvShow.adapter = movieAdapter
                        }
                        1 -> {
                            rvShow.adapter = tvShowAdapter
                        }
                    }
                }.launchIn(lifecycleScope)
        }
    }


    private fun showNoData(state: Boolean) {
        binding.ivNoData.visibility = if (state) View.VISIBLE else View.GONE
        binding.tvNoData.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun showNotFound(state: Boolean) {
        binding.ivNotFound.visibility = if (state) View.VISIBLE else View.GONE
        binding.tvNotFound.visibility = if (state) View.VISIBLE else View.GONE
    }

    private fun observeMovieSearch() {
        viewModel.searchMovieResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    showNoData(false)
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
                    showNoData(false)
                    response.data?.let {
                        showNoData(false)
                        movieAdapter.submitList(
                            DataMapper.mapMovieDomainsToPresenters(
                                it
                            )
                        )
                        binding.pbShow.visibility = View.GONE
                        if (it.isNotEmpty()) {
                            showNotFound(false)
                        } else {
                            showNotFound(true)
                        }
                    }
                }
            }
        }
    }

    private fun observeTvSearch() {
        viewModel.searchTvShowResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Error -> {
                    showNoData(false)
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
                        showNoData(false)
                        tvShowAdapter.submitList(
                            DataMapper.mapTvDomainsToPresenters(
                                it
                            )
                        )
                        binding.pbShow.visibility = View.GONE
                        if (it.isNotEmpty()) {
                            showNotFound(false)
                        } else {
                            showNotFound(true)
                        }
                    }
                }
            }
        }
    }
}
