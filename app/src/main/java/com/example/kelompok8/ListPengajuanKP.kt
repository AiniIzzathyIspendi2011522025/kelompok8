package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.adapter.PengajuanAdapter
import com.example.kelompok8.models.Pengajuan

class ListPengajuanKP : AppCompatActivity() {

    private lateinit var rvPengajuan : RecyclerView
    private lateinit var tempatList: ArrayList<Pengajuan>
    private lateinit var adapter: PengajuanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pengajuan_kp)

        rvPengajuan = findViewById(R.id.rv_pengajuan)
        rvPengajuan.layoutManager = LinearLayoutManager(this)

        tempatList = ArrayList()

        tempatList.add(Pengajuan("Semen Padang"))
        tempatList.add(Pengajuan("BKKBN"))
        tempatList.add(Pengajuan("BPS"))
        tempatList.add(Pengajuan("LLDIKTI"))


        adapter = PengajuanAdapter()
        rvPengajuan.adapter = adapter

    }

    fun onButtonTambahKP(view: View) {
        intent = Intent(this, FormPengajuanKP::class.java)
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