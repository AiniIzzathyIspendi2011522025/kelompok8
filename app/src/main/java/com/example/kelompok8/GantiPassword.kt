package com.example.kelompok8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.kelompok8.PojoModels.ChangePasswordResponse
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.UpdateProfilResponse
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.databinding.ActivityGantiPasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GantiPassword : AppCompatActivity() {

    lateinit var binding: ActivityGantiPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGantiPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onButtonSaveGantiPassword(view: View) {
        val sharedPref = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)?: return
        val token = sharedPref.getString("TOKEN",null)

        val old_password = binding.passwordlama.text.toString()
        val new_password = binding.passwordbaru.text.toString()
        val confirm_password = binding.confirmpassword.text.toString()

        val client: KPApi = config().getService()
        val call: Call<ChangePasswordResponse> = client.updatePassword(token = "Bearer $token", old_password, new_password,confirm_password)
        Log.d("update-debug", "$old_password|$new_password|$confirm_password|Bearer $token")

        call.enqueue(object: Callback<ChangePasswordResponse> {
            override fun onFailure(call: Call<ChangePasswordResponse>, t: Throwable) {
                Log.d("update-debug",t.localizedMessage)
            }
            override fun onResponse(call: Call<ChangePasswordResponse>, response: Response<ChangePasswordResponse>) {
                Log.d("update-debug", "response : $response")

                val respon: ChangePasswordResponse? = response.body()
                if (respon != null && respon.status == "success" && new_password == confirm_password) {

                    Log.d("update-debug", "$old_password:$new_password|$confirm_password|$token|$respon")

                    Toast.makeText(this@GantiPassword, "Berhasil Mengupdate password", Toast.LENGTH_SHORT).show()

                    intent = Intent(applicationContext, ProfilPengguna::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@GantiPassword, "Password & Confirm Passowrd Salah", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun onButtonHomeGantiPassword(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfilGantiPassword(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }
}