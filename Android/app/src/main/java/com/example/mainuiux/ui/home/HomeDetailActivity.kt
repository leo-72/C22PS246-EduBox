package com.example.mainuiux.ui.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mainuiux.R

class HomeDetailActivity : AppCompatActivity() {
    companion object{
        const val JUDUL = "judul"
        const val MATERI = "materi"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_detail)

        //judul
        val Judul = intent.getStringExtra(JUDUL)
        val judul = findViewById<TextView>(R.id.rvJudulDetailHome)

        //materi
        val Materi = intent.getStringExtra(MATERI)
        val materi = findViewById<TextView>(R.id.rvDetailHome)

        judul.text = Judul.toString()
        materi.text = Materi.toString()

    }

}