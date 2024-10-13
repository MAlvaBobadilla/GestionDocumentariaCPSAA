package com.example.gestindocumentariacpsaa.presentation.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

object Messages {
    fun genericToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
    fun createDialog(context: Context) = AlertDialog.Builder(context).create()
}