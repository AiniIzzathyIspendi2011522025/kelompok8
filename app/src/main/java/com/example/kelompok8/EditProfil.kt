package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class EditProfil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profil)
    }

    fun onButtonSaveEditProfil(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }

    fun onButtonHomeEditProfil(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfilEditProfil(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}