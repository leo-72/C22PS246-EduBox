package com.bangkit.eduboxloginregister.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bangkit.eduboxloginregister.databinding.ActivityMainBinding
import com.bangkit.eduboxloginregister.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("Users")
        firebaseUser = auth.currentUser!!

        showData()
        logout()
    }

    private fun showData() {

        val dataRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(Users::class.java)
                    binding.txtName.text = user?.name
                    binding.txtGender.text = user?.gender
                    binding.txtEmail.text = user?.email
                    binding.txtPhone.text = user?.phone
                    binding.txtClassUser.text = user?.classUser
                    binding.txtUsername.text = user?.username
                    binding.txtPassword.text = user?.password
                } else {
                    Toast.makeText(applicationContext, "Data not found", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(snapshot: DatabaseError) {

            }
        })
    }

    private fun logout() {
        binding.btnSignout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}