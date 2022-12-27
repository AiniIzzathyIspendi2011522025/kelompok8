package com.example.kelompok8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.UpdateProfilResponse
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.databinding.ActivityEditProfilBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditProfil : AppCompatActivity() {

    lateinit var binding: ActivityEditProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getNama = intent.getStringExtra("Nama")
        val getEmail = intent.getStringExtra("Email")

        binding.editNama.setText(getNama)
        binding.editMail.setText(getEmail)

    }

    fun onButtonSaveEditProfil(view: View) {


        val sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)?: return
        val token = sharedPref.getString("TOKEN",null)

        val name = binding.editNama.text.toString()
        val email = binding.editMail.text.toString()

        val client: KPApi = config().getService()
        val call: Call<UpdateProfilResponse> = client.updateProfile(token = "Bearer $token", name, email)
        Log.d("update-debug", "$name|$email|Bearer $token")

        call.enqueue(object: Callback<UpdateProfilResponse> {
            override fun onFailure(call: Call<UpdateProfilResponse>, t: Throwable) {
                Log.d("update-debug",t.localizedMessage)
            }
            override fun onResponse(call: Call<UpdateProfilResponse>, response: Response<UpdateProfilResponse>) {
                Log.d("update-debug", "response : $response")

                val respon: UpdateProfilResponse? = response.body()
                if (respon != null && respon.status == "success" ) {

                    Log.d("update-debug", "$name:$email|$token|$respon")

                    Toast.makeText(this@EditProfil, "Berhasil Mengupdate Profile", Toast.LENGTH_SHORT).show()

                    intent = Intent(applicationContext, ProfilPengguna::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@EditProfil, "Salah", Toast.LENGTH_SHORT).show()
                }
            }
        })

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