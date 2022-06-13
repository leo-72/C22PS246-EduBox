package com.example.mainuiux.ui.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.mainuiux.R

class QuizDetailActivity : AppCompatActivity() {
    companion object{
        const val JUDUL = "judul"
        const val SOAL = "soal"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_detail)

        //judul
        val Judul = intent.getStringExtra(JUDUL)
        val judul = findViewById<TextView>(R.id.rvJudulDetailKuis)

        //soal
        val Soal = intent.getStringExtra(SOAL)
        val soal = findViewById<TextView>(R.id.rvDetailKuis)

        judul.text = Judul.toString()
        soal.text = Soal.toString()

    }

}