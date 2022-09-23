package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Homescreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)
    }

    fun onButtonPengajuanKP(view: View) {
        intent = Intent(this, ListPengajuanKP::class.java)
        startActivity(intent)
    }

    fun onButtonDetailKP(view: View) {
        intent = Intent(this, DetailKP::class.java)
        startActivity(intent)
    }

    fun onButtonHome(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfil(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}