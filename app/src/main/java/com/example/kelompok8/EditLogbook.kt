package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EditLogbook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_logbook)
    }

    fun onButtonSaveEditLogbook(view: View) {
        intent = Intent(this, Logbook::class.java)
        startActivity(intent)
    }
    fun onButtonHomeEditLogbook(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }
    fun onButtonProfilEditLogbook(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}