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
import com.example.kelompok8.PojoModels.ListKP.InternshipItem
import com.example.kelompok8.PojoModels.ListKP.ListKPResponse
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.adapter.PengajuanAdapter
import com.example.kelompok8.databinding.ActivityHomescreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Homescreen : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    lateinit var binding: ActivityHomescreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomescreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PengajuanAdapter()

        val sharedToken = getSharedPreferences("sharedpref", Context.MODE_PRIVATE) ?: return
        val token = sharedToken.getString("TOKEN", null)

        recyclerView = binding.rvPengajuankp
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        Log.d("list-debug", token.toString())

        val client: KPApi = config().getService()
        val call: Call<ListKPResponse> = client.listkpresponse(token = "Bearer " + token)

        call.enqueue(object : Callback<ListKPResponse> {
            override fun onFailure(call: Call<ListKPResponse>, t: Throwable) {
                Log.d("list-debug", t.localizedMessage)
//                Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(
                call: Call<ListKPResponse>,
                response: Response<ListKPResponse>
            ) {
                val respon = response.body()
                if (respon != null) {
                    val list: List<InternshipItem> = respon.internship as List<InternshipItem>
                    adapter.setlistKP(list)
                    Log.d("list-debug", list.toString())
                }
                Log.d("list-debug", respon?.internship?.size.toString())
                Log.d("list-debug", "respon : " + respon?.internship.toString())

                adapter.setOnClickListener(object : PengajuanAdapter.onClickListener {

                    override fun onItemClick(position: Int) {
                        val position = respon?.internship?.get(position)
                        val sharedPref = getSharedPreferences("homescreenpref", MODE_PRIVATE) ?: return
                        with(sharedPref.edit()) {
                            putString("IDL", position?.id.toString())
                            apply()
                        }
                        Log.d("Detail-update", position.toString())
                        val intent = Intent(this@Homescreen, ListPengajuanKP::class.java)
//                        intent.putExtra("id_logbook", data[position].id)
//                detailLogbookIntent.putExtra("id_Extramagang",data[position].internshipId)
                        startActivity(intent)
                    }
                })
            }

        })
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

    fun onButtonListKP(view: View) {
        intent = Intent(this, ListPengajuanKP::class.java)
        startActivity(intent)
    }


}