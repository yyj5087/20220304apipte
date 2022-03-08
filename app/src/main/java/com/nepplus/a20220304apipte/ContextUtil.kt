package com.nepplus.a20220304apipte

import android.content.Context

class ContextUtil {
    companion object{
        private val prefName = "OkHttpPracticePref"

        private val TOKEN = "TOKEN"
        private val AUTO_LOGIN = "AUTO_LOGIN"

        fun setToken(context: Context,token: String){
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            pref.edit().putString(TOKEN,token).apply()
        }
        fun getToken(context: Context) : String {
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getString(TOKEN, "")!!
        }
        fun setAutoLogin(context: Context,isAuto: Boolean){
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            pref.edit().putBoolean(AUTO_LOGIN, isAuto).apply()
        }
        fun getAutoLogin(context: Context) : Boolean{
            val pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, true)

        }

    }

}