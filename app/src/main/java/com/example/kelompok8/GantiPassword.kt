package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GantiPassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ganti_password)
    }

    fun onButtonSaveGantiPassword(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }

    fun onButtonHomeGantiPassword(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfilGantiPassword(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}