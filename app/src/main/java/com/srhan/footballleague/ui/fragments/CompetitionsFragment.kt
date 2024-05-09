package com.srhan.footballleague.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.srhan.footballleague.data.model.Resource
import com.srhan.footballleague.databinding.FragmentCompetitionsBinding
import com.srhan.footballleague.domain.model.CompetitionDetailsModel
import com.srhan.footballleague.ui.adapter.CompetitionAdapter
import com.srhan.footballleague.ui.common.showSnakeBarError
import com.srhan.footballleague.ui.viewmodel.CompetitionViewModel
import com.srhan.footballleague.utils.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompetitionsFragment : Fragment(), CompetitionAdapter.OnCompetitionClickListener {

    private var _binding: FragmentCompetitionsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: CompetitionViewModel by viewModels()
    private val competitionAdapter: CompetitionAdapter by lazy {
        CompetitionAdapter().apply {
            setOnCompetitionClickListener(this@CompetitionsFragment)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCompetitionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkInternetConnection(requireContext())
        setUpRecyclerView()
    }

    private fun checkInternetConnection(context: Context) {
        if (isInternetAvailable(context)) {
            // Fetch data from the remote API
            fetchCompetitionsFromApi()
        } else {
            // Fetch data from the local database
            fetchCompetitionsFromLocalDatabase()
        }
    }

    private fun fetchCompetitionsFromLocalDatabase() {
        viewModel.getCompetitionsFromLocal()
        Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        lifecycleScope.launch {
            viewModel.competitionsLocalState.collect {
                updateUI(it)
            }

        }
    }

    private fun fetchCompetitionsFromApi() = lifecycleScope.launch {
        viewModel.competitionsNetworkState.collect {
            updateUI(it)
        }
    }

    private fun updateUI(resource: Resource<List<CompetitionDetailsModel>>) {
        when (resource) {
            is Resource.Loading -> {
                showLoading()
            }

            is Resource.Success -> {
                hideLoading()
                competitionAdapter.differ.submitList(resource.data)
            }

            is Resource.Error -> {
                hideLoading()
                view?.showSnakeBarError(resource.exception?.message.toString())
            }
        }
    }

    private fun showLoading() {
        binding.Shimmer.visibility = View.VISIBLE
        binding.Shimmer.startShimmer()

    }

    private fun hideLoading() {
        binding.Shimmer.visibility = View.GONE
        binding.Shimmer.stopShimmer()

    }

    private fun setUpRecyclerView() {
        binding.competitionRecycler.apply {
            adapter = competitionAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCompetitionClick(competition: CompetitionDetailsModel) {
        val action =
            CompetitionsFragmentDirections.actionCompetitionsFragmentToDetailsFragment(competition)
        findNavController().navigate(action)
    }


}