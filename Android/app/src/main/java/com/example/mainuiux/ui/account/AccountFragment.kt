package com.example.mainuiux.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mainuiux.databinding.FragmentAccountBinding
import com.example.mainuiux.model.Users
import com.example.mainuiux.ui.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class AccountFragment : Fragment() {
    private var _binding: FragmentAccountBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference
    private lateinit var firebaseUser: FirebaseUser

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnLogout.setOnClickListener{
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun showData() {

        val dataRef = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.uid)
        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val user = snapshot.getValue(Users::class.java)
                        binding.rvNamaAkun.text = user?.name
                        binding.rvKelasAkun.text = user?.classUser
                }
            }

            override fun onCancelled(snapshot: DatabaseError) {

            }
        })
    }
}