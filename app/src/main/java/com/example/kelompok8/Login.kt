package com.example.kelompok8

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.LoginResponse
import com.example.kelompok8.databinding.ActivityLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btnLogin = binding.btnSignIn

//        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
//            val TAG = "Login-Debug"
//            if (!task.isSuccessful) {
//                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
//                return@OnCompleteListener
//            }
//            // Get new FCM registration token
//            val token = task.result
//            // Log and toast

//            Log.d(TAG, token)
//            //Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
//        })

        btnLogin.setOnClickListener {
            //       createNotificationChannel()
//            intent = Intent(this, Homescreen::class.java)
//            startActivity(intent)
            val editusername : EditText = findViewById(R.id.editEmail)
            val editpassword : EditText = findViewById(R.id.editPassword)

            val username = editusername.text.toString()
            val password = editpassword.text.toString()

            val client : KPApi = config().getService()
            val call : Call<LoginResponse> = client.login(username, password)

            call.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    val respon : LoginResponse? = response.body()
                    if(respon != null && respon.status == "success"){

                        val token = respon.authorisation?.token
                        val user = respon.user

                        Log.d("Login-debug", "$username:$password|$token|$respon")

                        val sharedPref = getSharedPreferences("sharedpref", MODE_PRIVATE) ?:return
                        with (sharedPref.edit()) {
                            putString("TOKEN", token)
                            putString("USER", user.toString())
                            apply()
                        }
                        Toast.makeText(this@Login, "Login berhasil ${response.body()?.user?.name.toString()}", Toast.LENGTH_SHORT).show()
                        intent = Intent(this@Login, Homescreen::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this@Login, "Password atau username salah", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@Login, "Proses login gagal", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }

//    private fun createNotificationChannel() {
//        val CHANNEL_ID = "1"
//        // Create the NotificationChannel, but only on API 26+ because
//        // the NotificationChannel class is new and not in the support library
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val name = "Goo Hoomee"
//            val descriptionText = "Ini Notifikasi"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//            // Register the channel with the system
//            val notificationManager: NotificationManager =
//                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        val intent = Intent(this, Homescreen::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
//
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.home)
//            .setContentTitle("My notification")
//            .setContentText("Anda Berhasil Login!")
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//            // Set the intent that will fire when the user taps the notification
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//        with(NotificationManagerCompat.from(this)) {
//            // notificationId is a unique int for each notification that you must define
//            val notificationId = 1
//            notify(notificationId, builder.build())
//        }
//    }



}