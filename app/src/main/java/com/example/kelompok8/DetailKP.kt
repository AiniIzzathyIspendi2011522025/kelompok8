package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class DetailKP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kp)
    }

    fun onButtonLaporanSelesai(view: View) {
        intent = Intent(this, LaporanKP::class.java)
        startActivity(intent)
    }

    fun onButtonDataSeminar(view: View) {
        intent = Intent(this, DataSeminar::class.java)
        startActivity(intent)
    }

    fun onButtonBalasanKP(view: View) {
        intent = Intent(this, UploadBalasan::class.java)
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

    fun onButtonLogbook(view: View) {
        intent = Intent(this, Logbook::class.java)
        startActivity(intent)
    }
}