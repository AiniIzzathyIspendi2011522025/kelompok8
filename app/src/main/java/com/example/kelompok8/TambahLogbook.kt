package com.example.kelompok8

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.kelompok8.PojoModels.KPApi
import com.example.kelompok8.PojoModels.TambahLogbookResponse
import com.example.kelompok8.PojoModels.config
import com.example.kelompok8.databinding.ActivityLogbookBinding
import com.example.kelompok8.databinding.ActivityTambahLogbookBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TambahLogbook : AppCompatActivity() {

    lateinit var binding: ActivityTambahLogbookBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahLogbookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getKegiatan = binding.tambahKegiatan
    }


    fun onButtonHomeTambahLogbook(view: View) {
        intent = Intent(this, Homescreen::class.java)
        startActivity(intent)
    }

    fun onButtonProfilTambahLogbook(view: View) {
        intent = Intent(this, ProfilPengguna::class.java)
        startActivity(intent)
    }

    fun onButtonSaveTambahLogbook(view: View) {
        createNotificationChannel()
        val sharedToken = getSharedPreferences("sharedpref", Context.MODE_PRIVATE)?: return
        val token = sharedToken.getString("TOKEN",null)

        val kegiatan = binding.tambahKegiatan.text.toString()

        Log.d("tambah-debug", "$kegiatan|Bearer $token")

        val client: KPApi = config().getService()
        val call: Call<TambahLogbookResponse> = client.tambah_logbook(token = "Bearer $token", 5, kegiatan)
        Log.d("tambah-debug", "$kegiatan|Bearer $token")

        call.enqueue(object: Callback<TambahLogbookResponse> {
            override fun onFailure(call: Call<TambahLogbookResponse>, t: Throwable) {
                Log.d("tambah-debug",t.localizedMessage)
            }
            override fun onResponse(
                call: Call<TambahLogbookResponse>,
                response: Response<TambahLogbookResponse>
            ) {
                Log.d("tambah-debug", "response : $response")

                val respon: TambahLogbookResponse? = response.body()
                if (respon != null && respon.status == "success" ) {

                    Log.d("tambah-debug", "$token|$respon")

                    Toast.makeText(this@TambahLogbook, "Berhasil Mengupdate Respon", Toast.LENGTH_SHORT).show()

                    intent = Intent(applicationContext, Logbook::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(this@TambahLogbook, "Salah", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun createNotificationChannel() {
        val CHANNEL_ID = "1"
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Goo Hoomee"
            val descriptionText = "Ini Notifikasi"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, Logbook::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.home)
            .setContentTitle("My notification")
            .setContentText("Logbook berhasil ditambahkan!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            // Set the intent that will fire when the user taps the notification
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            val notificationId = 1
            notify(notificationId, builder.build())
        }
    }

}