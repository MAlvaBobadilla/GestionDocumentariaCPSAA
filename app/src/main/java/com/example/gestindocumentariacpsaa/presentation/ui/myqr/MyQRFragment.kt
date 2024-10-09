package com.example.gestindocumentariacpsaa.presentation.ui.myqr

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gestindocumentariacpsaa.databinding.FragmentMyqrBinding
import com.example.gestindocumentariacpsaa.presentation.ui.myqr.adapter.CategoriesAdapter
import com.example.gestindocumentariacpsaa.presentation.utils.Messages
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder


class MyQRFragment : Fragment() {

    private val categoriesViewModel: CategoriesViewModel by viewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter

    private var _binding: FragmentMyqrBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyqrBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        val dni = "74278537"
        val qrImage = binding.imvCodigo

        generateCode(dni, qrImage)
    }

    private fun generateCode(dni: String, qrImage: ImageView) {
        try {
            val barEncode = BarcodeEncoder()
            val bitmap = barEncode.encodeBitmap(dni, BarcodeFormat.QR_CODE, 400, 400)
            qrImage.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            Log.d("TAG", "generateCode: ${e.message}")
        }
    }


}