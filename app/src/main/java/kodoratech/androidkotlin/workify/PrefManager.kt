package kodoratech.androidkotlin.workify

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

/**
 * Created by chadrac on 1/24/19.
 */
class PrefManager {
    lateinit var con: Context
    lateinit var pref: SharedPreferences

    constructor(con: Context){
        this.con = con
        getSP()
    }

    private fun getSP() {
        pref = con.getSharedPreferences(con.getString(R.string.pref_name), Context.MODE_PRIVATE)
    }

    fun writeSP(){
        var editor: SharedPreferences.Editor = pref.edit()
        editor.putString(con.getString(R.string.pref_key), "Suivant")
        editor.commit()
    }

    fun checkPreference() : Boolean{
        var status: Boolean = false
        status = !pref.getString(con.getString(R.string.pref_key), "").equals("")
        return status
    }

    fun clearPreference(){
        pref.edit().clear().commit()
        con.startActivity(Intent(con, MainActivity::class.java))
        (con as MainActivity).finish()
    }
}