package com.example.kelompok8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.LogoutResponse
import com.example.kelompok8.PojoModels.User
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.databinding.ActivityProfilPenggunaBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilPengguna : AppCompatActivity() {

    lateinit var binding: ActivityProfilPenggunaBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        val sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)?:return
        val token = sharedPref.getString("TOKEN", " ")


        Log.d("profile", token.toString())
        super.onCreate(savedInstanceState)
        binding = ActivityProfilPenggunaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val client : KPApi = config().getService()
        val call : Call<User> = client.profile("Bearer "+token)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                val respon = response.body()
                Log.d("profile-debug", "respon : "+ respon)

                val getNama = respon?.name
                binding.username.text = getNama

                val email = respon?.email
                binding.email.text = email

                val username = respon?.username
                binding.nim.text = username

            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("profile-debug", t.localizedMessage)
            }

        })

        val logout = binding.logout

        logout.setOnClickListener {
            if (token != null) {
                val client : KPApi = config().getService()
                val call : Call<LogoutResponse> = client.logout(token = "Bearer " + token)

                call.enqueue(object : Callback<LogoutResponse> {
                    override fun onResponse(
                        call: Call<LogoutResponse>,
                        response: Response<LogoutResponse>
                    ) {
                        val respon = response.body()
                        Log.d("profile-debug", "respon : " + respon)

                        val sharedPref =
                            getSharedPreferences("sharedpref", Context.MODE_PRIVATE) ?: return
                        with(sharedPref.edit()) {
                            putString("TOKEN", null)
                            apply()
                        }
                        Log.d("logout-debug",  "respon : "+ respon )
                        Toast.makeText(this@ProfilPengguna, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                        val logout = Intent(this@ProfilPengguna, Login::class.java)
                        logout.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(logout)
                        finish()
                    }
                    override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                        Log.d("logout-debug", t.localizedMessage)
                    }

                })
            }
        }


    }

    fun onButtonEditProfil(view: View) {
        intent = Intent(this, EditProfil::class.java)
        startActivity(intent)
    }

    fun onButtonHomeProfil(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfilProfil(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }

    fun onTextGantiPassword(view: View) {
        intent = Intent(this, GantiPassword::class.java)
        startActivity(intent)
    }

//    fun onLogout(view: View){
//        intent = Intent(this, Login::class.java)
//        startActivity(intent)
//    }
}