package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class DetailLogbook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_logbook)

        val headingTanggal : TextView = findViewById(R.id.textView11)

        val bundle : Bundle? = intent.extras
        val tgl = bundle!!.getString("tanggal")

        headingTanggal.text = tgl
    }



    fun onButtonEditDetailLogbook(view: View) {
        intent = Intent(this, EditLogbook::class.java)
        startActivity(intent)
    }
    fun onButtonHomeDetailLogbook(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }
    fun onButtonProfilDetailLogbook(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}