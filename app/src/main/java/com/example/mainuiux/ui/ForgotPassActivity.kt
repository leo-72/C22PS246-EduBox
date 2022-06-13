package com.example.mainuiux.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainuiux.R
import com.example.mainuiux.databinding.ActivityForgotPassBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ForgotPassActivity : AppCompatActivity() {


    private lateinit var binding: ActivityForgotPassBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("Users")

        setBtnEnable()
        editTextListener()
        moveToLoginActivity()
        moveToRegisterActivity()

    }

    private fun sendLink() {
        binding.btnSendLink.setOnClickListener {
            isEmailChecked()
        }
    }

    private fun isEmailChecked() {
        binding.progressBar.visibility = ProgressBar.VISIBLE
        val email = binding.inpEmail.text.toString()
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.visibility = ProgressBar.GONE
                    Toast.makeText(this, getString(R.string.check_email), Toast.LENGTH_LONG).show()
                } else {
                    binding.progressBar.visibility = ProgressBar.GONE
                    Toast.makeText(this, getString(R.string.email_not_found), Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun moveToLoginActivity() {
        binding.txtHaveAcc.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun moveToRegisterActivity() {
        binding.btnNewAcc.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun editTextListener() {
        binding.apply {
            inpEmail.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    //do nothing
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    //do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    setBtnEnable()
                }
            })
            sendLink()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun setBtnEnable() {
        binding.apply {
            val email = inpEmail.text.toString()

            btnSendLink.isEnabled =
                email.isNotEmpty() && isEmailValid(email)
        }
    }
}