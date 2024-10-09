package com.example.gestindocumentariacpsaa.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gestindocumentariacpsaa.databinding.FragmentHomeBinding
import com.example.gestindocumentariacpsaa.presentation.ui.home.adapter.HomeAdapter
import com.example.gestindocumentariacpsaa.presentation.utils.Messages
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var homeAdapter: HomeAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var colaborador: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initIntents()
        initUi()
    }

    private fun initUi() {
        initUiState()
        initRecyclerView()
        initListeners()
    }

    private fun initIntents() {

        arguments?.let {
            colaborador = it.getString("usuario")
        }
    }

    private fun initUiState() {

    }

    private fun initListeners() {
        onPlantaListener()
        onAreaListener()
    }

    private fun onPlantaListener() {
        binding.edtPlanta.setOnClickListener {
            homeViewModel.getPlantas()
        }
    }

    private fun onAreaListener() {
        binding.edtArea.setOnClickListener {
//            homeViewModel.getAreas(binding.edtPlanta.text.toString())
            homeViewModel.getAreas("P201")
        }
    }

    private fun successState(data: HomeStates.Success) {
        binding.pb.isVisible = false
//        homeAdapter.updateList(data)
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        Messages.genericToast(requireContext(), message = error)
    }

    private fun initRecyclerView() {
        homeAdapter = HomeAdapter()
//        binding.rcv.apply {
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = homeAdapter
//        }
    }


}