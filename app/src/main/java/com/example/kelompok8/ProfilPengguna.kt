package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class ProfilPengguna : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil_pengguna)
    }

    fun onButtonEditProfil(view: View) {
        intent = Intent(this, EditProfil::class.java)
        startActivity(intent)
    }

    fun onButtonHomeProfil(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfilProfil(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }

    fun onTextGantiPassword(view: View) {
        intent = Intent(this, GantiPassword::class.java)
        startActivity(intent)
    }

    fun onLogout(view: View){
        intent = Intent(this, Login::class.java)
        startActivity(intent)
    }
}