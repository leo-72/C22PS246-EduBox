package com.bangkit.eduboxloginregister.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.eduboxloginregister.R
import com.bangkit.eduboxloginregister.databinding.ActivityLoginBinding
import com.bangkit.eduboxloginregister.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("Users")

        setBtnEnable()
        editTextListener()
        moveToForgotPassword()
        moveToRegister()
    }

    private fun editTextListener() {
        binding.apply {
            inpEmail.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    //do nothing
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    setBtnEnable()
                }

                override fun afterTextChanged(p0: Editable?) {
                    //do nothing
                }
            })
            inpPassword.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    //do nothing
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    setBtnEnable()
                }

                override fun afterTextChanged(p0: Editable?) {
                    //do nothing
                }
            })
            moveToMain()
        }
    }

    private fun moveToMain() {
        binding.btnSignIn.setOnClickListener {
            isFormChecked()
        }
    }

    private fun moveToRegister() {
        binding.btnNewAcc.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveToForgotPassword() {
        binding.txtForgotPass.setOnClickListener {
            val intent = Intent(this, ForgotPassActivity::class.java)
            startActivity(intent)
        }
    }

    private fun isDataChecked() {
        binding.progressBar.visibility = ProgressBar.VISIBLE
        val email = binding.inpEmail.text.toString().trim()
        val password = binding.inpPassword.text.toString().trim()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    binding.progressBar.visibility = ProgressBar.GONE
                    firebaseUser = auth.currentUser!!
                    ref.child(firebaseUser.uid).addValueEventListener(object : ValueEventListener {
                        override fun onCancelled(error: DatabaseError) {
                            Log.e("Error", error.toString())
                        }

                        override fun onDataChange(snapshot: DataSnapshot) {
                            val emailUser = snapshot.getValue(Users::class.java)?.email
                            val passUser = snapshot.getValue(Users::class.java)?.password
                            if (emailUser != null || email == emailUser) {
                                if (passUser != null || password == passUser) {
                                    val intent =
                                        Intent(this@LoginActivity, MainActivity::class.java)
                                    startActivity(intent)
                                } else {
                                    binding.inpPassword.error = getString(R.string.pass_not_correct)
                                    binding.progressBar.requestFocus()
                                }
                            } else {
                                binding.inpEmail.error = getString(R.string.email_not_found)
                                binding.progressBar.requestFocus()
                            }
                        }

                    })
                } else {
                    binding.progressBar.visibility = ProgressBar.GONE
                    Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun isFormChecked() {
        binding.progressBar.visibility = ProgressBar.VISIBLE
        val email = binding.inpEmail.text.toString()
        val password = binding.inpPassword.text.toString()
        if (email.isEmpty()) {
            binding.progressBar.visibility = ProgressBar.GONE
            binding.inpEmail.error = getString(R.string.email_empty)
            binding.inpEmail.requestFocus()
        }
        if (password.isEmpty()) {
            binding.progressBar.visibility = ProgressBar.GONE
            binding.inpPassword.error = getString(R.string.pass_empty)
            binding.inpPassword.requestFocus()
        } else {
            isDataChecked()
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    private fun setBtnEnable() {
        binding.apply {
            val email = inpEmail.text.toString()
            val pass = inpPassword.text.toString()

            btnSignIn.isEnabled =
                email.isNotEmpty() && pass.isNotEmpty() && isPasswordValid(pass) && isEmailValid(
                    email
                )
        }
    }
}