package com.example.kelompok8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.ListLogbookResponse
import com.example.kelompok8.PojoModels.LogbooksItem
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.adapter.LogbookAdapter
import com.example.kelompok8.databinding.ActivityLogbookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Logbook : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var binding: ActivityLogbookBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = LogbookAdapter()

        val sharedToken = getSharedPreferences("sharedpref", Context.MODE_PRIVATE) ?: return
        val token = sharedToken.getString("TOKEN", null)

        recyclerView = binding.rvLogbook
//
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        Log.d("list-debug", token.toString())

        val client: KPApi = config().getService()
        val call: Call<ListLogbookResponse> = client.list_logbook(token = "Bearer " + token, id = 5)

        call.enqueue(object : Callback<ListLogbookResponse> {
            override fun onFailure(call: Call<ListLogbookResponse>, t: Throwable) {
                Log.d("list-debug", t.localizedMessage)
//                Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ListLogbookResponse>,
                response: Response<ListLogbookResponse>
            ) {
                val respon = response.body()
                if (respon != null) {
                    val list: List<LogbooksItem> = respon.logbooks as List<LogbooksItem>
                    adapter.setlistLogbook(list)
                    Log.d("list-debug", list.toString())
                }
                Log.d("list-debug", respon?.logbooks?.size.toString())
                Log.d("list-debug", "respon : " + respon?.logbooks.toString())

                adapter.setOnClickListener(object : LogbookAdapter.onClickListener {

                    override fun onItemClick(position: Int) {
                        val position = respon?.logbooks?.get(position)
                        val sharedPref = getSharedPreferences("logbookpref", MODE_PRIVATE) ?: return
                        with(sharedPref.edit()) {
                            putString("IDL", position?.id.toString())
                            apply()
                        }
                        Log.d("Detail-update", position.toString())
                        val intent = Intent(this@Logbook, DetailLogbook::class.java)
//                        intent.putExtra("id_logbook", data[position].id)
//                detailLogbookIntent.putExtra("id_Extramagang",data[position].internshipId)
                        startActivity(intent)
                    }
                })
            }

        })

        adapter.setOnClickListener(object : LogbookAdapter.onClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@Logbook, DetailLogbook::class.java)

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