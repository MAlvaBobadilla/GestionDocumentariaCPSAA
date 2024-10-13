package com.example.gestindocumentariacpsaa.presentation.ui.asistencias

import android.app.TimePickerDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gestindocumentariacpsaa.data.preferences.UserPreferences
import com.example.gestindocumentariacpsaa.databinding.DialogGeneralBinding
import com.example.gestindocumentariacpsaa.databinding.FragmentAsistenciaBinding
import com.example.gestindocumentariacpsaa.presentation.models.AsistenciaModel
import com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.asistencia.AsistenciaAdapter
import com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.tipoAsistencia.TipoAsistenciaAdapter
import com.example.gestindocumentariacpsaa.presentation.ui.asistencias.adapter.tipoDescripcion.TipoDescripcionAdapter
import com.example.gestindocumentariacpsaa.presentation.utils.Messages
import java.util.Calendar


class AsistenciaFragment : Fragment() {

    private lateinit var tipoAsistenciaAdapter: TipoAsistenciaAdapter
    private lateinit var tipoAsistenciaDialog: AlertDialog
    private lateinit var tipoDescripcionAdapter: TipoDescripcionAdapter
    private lateinit var tipoDescripcionDialog: AlertDialog
    private lateinit var asistenciaAdapter: AsistenciaAdapter

    private var _binding: FragmentAsistenciaBinding? = null
    private val binding get() = _binding!!

    private lateinit var dialogBinding: DialogGeneralBinding

    private var asistencia: MutableList<AsistenciaModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAsistenciaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initValues()
    }

    private fun initValues() {
        val nombre = UserPreferences.getUser(requireContext())!!.nombreCompleto
        val planta = UserPreferences.getUser(requireContext())!!.planta
        initUI(nombre, planta)
    }

    private fun initUI(nombre: String, planta: String) {
        binding.tvNombre.text = nombre
        binding.tvPlanta.text = planta
        initRecyclerView()
        initListeners()
    }

    private fun initRecyclerView() {
        tipoAsistenciaAdapter = TipoAsistenciaAdapter(onItemSelected = {
            binding.edtTipoAsistencia.setText(it)
            tipoAsistenciaDialog.dismiss()
            binding.tlSeleccion.visibility = View.VISIBLE
            if (it == "Charlas de Seguridad") {
                binding.edtSeleccion.setText("Seleccione Charla de Seguridad")
            } else {
                binding.edtSeleccion.setText("Seleccione Capacitación")
            }
        })
        tipoDescripcionAdapter = TipoDescripcionAdapter(onItemSelected = {
            binding.edtSeleccion.setText(it)
            tipoDescripcionDialog.dismiss()
        })
        asistenciaAdapter = AsistenciaAdapter()
        binding.rcv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = asistenciaAdapter
        }
    }

    private fun initListeners() {
        onTipoAsistenciaClick()
        onTipoSeleccionClick()
        onRegistrarClick()
        onHoraManualChecked()
    }

    private fun onHoraManualChecked() {
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.tlHoraManual.visibility = View.VISIBLE
                binding.edtHoraManual.setOnClickListener {
                    val calendar = Calendar.getInstance()
                    val hora = calendar.get(Calendar.HOUR_OF_DAY)
                    val minutos = calendar.get(Calendar.MINUTE)

                    val timePickerDialog = TimePickerDialog(
                        requireContext(),
                        { _, hourOfDay, minute ->
                            val horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute)
                            binding.edtHoraManual.setText(horaSeleccionada)
                        }, hora, minutos, true
                    )
                    timePickerDialog.show()
                }
            } else {
                binding.tlHoraManual.visibility = View.GONE
            }
        }
    }

    private fun onRegistrarClick() {
        binding.btnRegistrar.setOnClickListener {
            asistenciaAdapter.updateList(asistencia)

            val documento = binding.edtDocumento.text.toString()
            val tipoAsistencia = binding.edtTipoAsistencia.text.toString()
            val tipoSeleccion = binding.edtSeleccion.text.toString()

            val isHoraManualChecked = binding.checkbox.isChecked
            val horaActual: String

            if (isHoraManualChecked) {
                val horaSeleccionada = binding.edtHoraManual.text.toString()
                if (horaSeleccionada.isEmpty()) {
                    Messages.genericToast(requireContext(), "Seleccione una Hora")
                    return@setOnClickListener
                }
                horaActual = horaSeleccionada
            } else {
                val calendar = Calendar.getInstance()
                val hora = calendar.get(Calendar.HOUR_OF_DAY)
                val minutos = calendar.get(Calendar.MINUTE)
                val segundos = calendar.get(Calendar.SECOND)

                horaActual = String.format("%02d:%02d:%02d", hora, minutos, segundos)
            }

            // Crear el nuevo registro
            val nuevoRegistro =
                AsistenciaModel(dni = documento, asistencia = tipoSeleccion, hora = horaActual)

            // Agregar el nuevo registro a la lista
            asistencia.add(nuevoRegistro)
            asistenciaAdapter.updateList(asistencia)
        }
    }

    private fun onTipoAsistenciaClick() {
        binding.edtTipoAsistencia.setOnClickListener {
            tipoAsistenciaDialog = Messages.createDialog(requireContext())
            tipoAsistenciaAdapter.updateList(listOf("Charlas de Seguridad", "Capacitación"))
            dialogTipoAsistencia()
        }
    }

    private fun dialogTipoAsistencia() {
        val viewPool = RecyclerView.RecycledViewPool()
        dialogBinding = DialogGeneralBinding.inflate(layoutInflater)
        tipoAsistenciaDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBinding.rcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setRecycledViewPool(viewPool)
            recycledViewPool.setMaxRecycledViews(0, 0)
            adapter = tipoAsistenciaAdapter
        }
        dialogBinding.txtDialogTittle.text = "Tipo Asistencia"
        tipoAsistenciaDialog.setView(dialogBinding.root)
        tipoAsistenciaDialog.show()
    }

    private fun onTipoSeleccionClick() {
        binding.edtSeleccion.setOnClickListener {
            tipoDescripcionDialog = Messages.createDialog(requireContext())
            if (binding.edtTipoAsistencia.text.toString() == "Charlas de Seguridad") {
                tipoDescripcionAdapter.updateList(
                    listOf(
                        "Charla de Seguridad 1",
                        "Charla de Seguridad 2",
                        "Charla de Seguridad 3"
                    )
                )
            } else {
                tipoDescripcionAdapter.updateList(
                    listOf(
                        "Capacitación 1",
                        "Capacitación 2",
                        "Capacitación 3"
                    )
                )
            }
            dialogTipoDescripcion()
        }
    }

    private fun dialogTipoDescripcion() {
        val viewPool = RecyclerView.RecycledViewPool()
        dialogBinding = DialogGeneralBinding.inflate(layoutInflater)
        tipoDescripcionDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBinding.rcv.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
            setRecycledViewPool(viewPool)
            recycledViewPool.setMaxRecycledViews(0, 0)
            adapter = tipoDescripcionAdapter
        }
        dialogBinding.txtDialogTittle.text = "Tipo Descripcion"
        tipoDescripcionDialog.setView(dialogBinding.root)
        tipoDescripcionDialog.show()
    }

}