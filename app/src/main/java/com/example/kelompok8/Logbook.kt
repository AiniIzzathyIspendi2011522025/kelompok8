package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Logbook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook)
    }

    fun onButtonTambahLogbook(view: View) {
        intent = Intent(this, TambahLogbook::class.java)
        startActivity(intent)
    }
    fun onButtonHomeLogbook(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }
    fun onButtonProfilLogbook(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}