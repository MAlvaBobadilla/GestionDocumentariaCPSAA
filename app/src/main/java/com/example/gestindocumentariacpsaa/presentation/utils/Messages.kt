package com.example.gestindocumentariacpsaa.presentation.utils

import android.content.Context
import android.widget.Toast

object Messages {
    fun genericToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}