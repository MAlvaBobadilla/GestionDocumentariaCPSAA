package com.example.gestindocumentariacpsaa.presentation.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.databinding.DialogGeneralBinding
import com.example.gestindocumentariacpsaa.databinding.FragmentHomeBinding
import com.example.gestindocumentariacpsaa.presentation.ui.home.adapter.AreasAdapter
import com.example.gestindocumentariacpsaa.presentation.ui.home.adapter.PlantaAdapter
import com.example.gestindocumentariacpsaa.presentation.ui.home.homeStates.AreaStates
import com.example.gestindocumentariacpsaa.presentation.ui.home.homeStates.PlantaStates
import com.example.gestindocumentariacpsaa.presentation.utils.Messages
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private lateinit var plantaAdapter: PlantaAdapter
    private lateinit var plantasDialog: AlertDialog
    private lateinit var areasAdapter: AreasAdapter
    private lateinit var areasDialog: AlertDialog

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var dialogBinding: DialogGeneralBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        initUiState()
        initRecyclerView()
        initListeners()
    }

    private fun initUiState() {
    }

    private fun initListeners() {
        onPlantaListener()
        onAreaListener()
        onBotonListener()
    }

    private fun onPlantaListener() {
        binding.edtPlanta.setOnClickListener {
            lifecycleScope.launch {
                if (homeViewModel.plantas.isNullOrEmpty()) {
                    homeViewModel.getPlantas()
                    plantasDialog = Messages.createDialog(requireContext())
                    homeViewModel.plantaState.collectLatest {
                        when (it) {
                            is PlantaStates.Success -> successPlantasState(it)
                            is PlantaStates.Loading -> loadingState()
                            is PlantaStates.Error -> errorState(it.error)
                        }
                    }
                } else {
                    dialogPlantas()
                }
            }
        }
    }

    private fun onAreaListener() {
        binding.edtArea.setOnClickListener {
            lifecycleScope.launch {
                areasDialog = Messages.createDialog(requireContext())
                homeViewModel.areaState.collect {
                    when (it) {
                        is AreaStates.Success -> successAreasState(it)
                        is AreaStates.Loading -> loadingState()
                        is AreaStates.Error -> errorState(it.error)
                    }
                }
            }
        }
    }

    private fun onBotonListener() {
        binding.btnSincronizar.setOnClickListener {
            onButtonPressed()
        }
    }

    private fun onButtonPressed() {
        Messages.genericToast(
            requireContext(),
            message = "Se sincronizaron los datos correctamente"
        )
    }

    private fun successPlantasState(data: PlantaStates.Success) {
        binding.pb.isVisible = false
        plantaAdapter.updateList(data.plantas)
        Messages.genericToast(requireContext(), message = data.successMessage)
        dialogPlantas()
    }

    private fun successAreasState(data: AreaStates.Success) {
        binding.pb.isVisible = false
        areasAdapter.updateList(data.areas)
        Messages.genericToast(requireContext(), message = data.successMessage)
        dialogAreas()
    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun errorState(error: String) {
        binding.pb.isVisible = false
        Messages.genericToast(requireContext(), message = error)
    }

    private fun dialogPlantas() {
        val viewPool = RecyclerView.RecycledViewPool()
        dialogBinding = DialogGeneralBinding.inflate(layoutInflater)
        plantasDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBinding.rcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setRecycledViewPool(viewPool)
            recycledViewPool.setMaxRecycledViews(0, 0)
            adapter = plantaAdapter
        }
        dialogBinding.txtDialogTittle.text = "PLANTAS"
        plantasDialog.setView(dialogBinding.root)
        plantasDialog.show()
    }

    private fun dialogAreas() {
        val viewPool = RecyclerView.RecycledViewPool()
        dialogBinding = DialogGeneralBinding.inflate(layoutInflater)
        areasDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialogBinding.rcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setRecycledViewPool(viewPool)
            recycledViewPool.setMaxRecycledViews(0, 0)
            adapter = areasAdapter
        }
        dialogBinding.txtDialogTittle.text = "AREAS"

        areasDialog.setView(dialogBinding.root)
        areasDialog.show()
    }

    private fun initRecyclerView() {
        plantaAdapter = PlantaAdapter(onItemSelected = {
            binding.edtPlanta.setText(it.planta)
            plantasDialog.dismiss()
            homeViewModel.getAreas(it.id)
        })
        areasAdapter = AreasAdapter(onItemSelected = {
            binding.edtArea.setText(it)
            areasDialog.dismiss()
        })
    }

}