package com.example.gestindocumentariacpsaa.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.gestindocumentariacpsaa.data.models.Response
import com.google.gson.Gson

object UserPreferences {
    val USER_PREFERENCIAS ="UsuarioPref"
    val CLASS_NAME = "Usuario"

    fun getUser(context: Context): Response.ColaboradorInfo?{
        val pref: SharedPreferences = context.getSharedPreferences(USER_PREFERENCIAS,0)
        if(pref.contains(CLASS_NAME)){
            val login: String? = pref.getString(CLASS_NAME,"")
            val gson = Gson()
            var user = gson.fromJson(login, Response.ColaboradorInfo::class.java)
            // Log para ver qué estás obteniendo
            Log.d("UserPreferences", "Retrieved user: $login")

            return user
        }else {
            // Log para saber si no encontró nada
            Log.d("UserPreferences", "No user found in SharedPreferences")
        }

        return null
    }

    fun setUser(context: Context,usuario: Response.ColaboradorInfo):Int{
        val pref = context.getSharedPreferences(USER_PREFERENCIAS, 0) // 0 - for private mode
        val editor = pref.edit()
        val gson = Gson()
        val user = gson.toJson(usuario)

        // Log para verificar qué datos estás almacenando
        Log.d("UserPreferences", "Storing user: $user")
        editor.putString(CLASS_NAME, user)
        editor.apply()
        Log.d("UserPreferences", "User has been saved")
        return 1
    }

    fun clearUser(context: Context){
        val pref = context.getSharedPreferences(USER_PREFERENCIAS, 0) // 0 - for private mode
        val editor = pref.edit()
        editor.clear()
        editor.apply()
    }
}