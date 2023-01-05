package com.example.kelompok8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.kelompok8.PojoModels.DetailLogbookResponse
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.databinding.ActivityDetailLogbookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

lateinit var binding: ActivityDetailLogbookBinding

class DetailLogbook : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailLogbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("mahasiswapref", Context.MODE_PRIVATE)?: return
        val sharedToken = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)?: return
        val logbookpref = getSharedPreferences("logbookpref", Context.MODE_PRIVATE)?: return

        val Nama = sharedPref.getString("NAMA",null)
        val Nim = sharedPref.getString("NIM",null)
        //id intern
        val Id = sharedPref.getInt("ID", 0)

        //token
        val token = sharedToken.getString("TOKEN", null)

        //id logbook
        val IDL = logbookpref.getString("IDL",null)
        Log.d("Detail-debug","responnn  "+ IDL.toString())


        val client: KPApi = config().getService()
        val call: Call<DetailLogbookResponse> = client.detail_logbook(token = "Bearer $token", 5, IDL)

        call.enqueue(object : Callback<DetailLogbookResponse> {
            override fun onFailure(call: Call<DetailLogbookResponse>, t: Throwable) {
                Log.d("detail-debug", t.localizedMessage)
//                Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<DetailLogbookResponse>, response: Response<DetailLogbookResponse>) {
                val respon = response.body()
                if(respon != null){
                    Log.d("Detail-debug", respon.toString())
                    binding.detailKegiatan.text = respon.logbook?.activities

                }

            }

        })

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