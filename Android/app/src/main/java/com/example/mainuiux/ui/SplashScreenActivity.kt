package com.example.mainuiux.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.mainuiux.R
import com.example.mainuiux.databinding.ActivitySplashScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ref = FirebaseDatabase.getInstance().getReference("Users")

        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim)
        val botAnim = AnimationUtils.loadAnimation(this, R.anim.bot_anim)
        binding.exampleLogo.startAnimation(topAnim)
        binding.exampleText.startAnimation(botAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            moveToAnotherActivity()
            finish()
        }, 2000)
    }

    private fun moveToAnotherActivity() {
        FirebaseAuth.getInstance().currentUser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } ?: run {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}