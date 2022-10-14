package com.example.kelompok8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.adapter.LogbookAdapter
import com.example.kelompok8.models.Logbook

class Logbook : AppCompatActivity() {

    private lateinit var rvLogbook : RecyclerView
    private lateinit var logbookList : ArrayList<Logbook>
    private lateinit var adapter: LogbookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook)

        rvLogbook = findViewById(R.id.rv_logbook)
        rvLogbook.layoutManager = LinearLayoutManager(this)

        logbookList = ArrayList()

        logbookList.add(Logbook("10 Oktober 2022"))
        logbookList.add(Logbook("11 Oktober 2022"))
        logbookList.add(Logbook("12 Oktober 2022"))
        logbookList.add(Logbook("13 Oktober 2022"))
        logbookList.add(Logbook("14 Oktober 2022"))
        logbookList.add(Logbook("15 Oktober 2022"))

        adapter = LogbookAdapter(logbookList)
        rvLogbook.adapter = adapter
        adapter.setonItemClickListener(object : LogbookAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent (this@Logbook, DetailLogbook::class.java)
                intent.putExtra("tanggal", logbookList[position].tanggal)
                startActivity(intent)
            }

        })
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