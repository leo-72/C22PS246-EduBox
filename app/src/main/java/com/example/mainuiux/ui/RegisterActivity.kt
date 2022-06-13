package com.example.mainuiux.ui
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mainuiux.R
import com.example.mainuiux.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var ref: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        ref = FirebaseDatabase.getInstance().getReference("Users")

        setBtnEnable()
        editTextListener()

        binding.txtHaveAnAccount.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }

    private fun confirmData() {
        binding.apply {
            val name = inpFullname.text.toString().trim()
            val gender = inpGender.text.toString().trim()
            val email = inpEmail.text.toString().trim()
            val phone = inpPhone.text.toString().trim()
            val classUser = inpClass.text.toString().trim()
            val username = inpUsername.text.toString().trim()
            val password = inpPassword.text.toString().trim()
            val rePass = inpRePassword.text.toString().trim()

            if (name.isEmpty()) {
                inpFullname.error = getString(R.string.fullnameEmpty)
                inpFullname.requestFocus()
            }

            if (gender.isEmpty()) {
                inpGender.error = getString(R.string.genderEmpty)
                inpGender.requestFocus()
            }

            if (email.isEmpty()) {
                inpEmail.error = getString(R.string.email_required)
                inpEmail.requestFocus()
            }

            if (phone.isEmpty()) {
                inpPhone.error = getString(R.string.phoneEmpty)
                inpPhone.requestFocus()
            }

            if (classUser.isEmpty()) {
                inpClass.error = getString(R.string.classEmpty)
                inpClass.requestFocus()
            }

            if (username.isEmpty()) {
                inpUsername.error = getString(R.string.usernameEmpty)
                inpUsername.requestFocus()
            }

            if (password.isEmpty()) {
                inpPassword.error = getString(R.string.password_required)
                inpPassword.requestFocus()
            }

            if (rePass.isEmpty()) {
                inpRePassword.error = getString(R.string.re_password_required)
                inpRePassword.requestFocus()
            }

            progressBar.visibility = View.VISIBLE
            if (password == rePass) {
                registrationUser(name, gender, email, phone, classUser, username, password)
            } else {
                progressBar.visibility = View.GONE
                inpRePassword.error = getString(R.string.password_not_match)
                inpRePassword.requestFocus()
                return
            }
        }
    }

    interface UserExistsCallback {
        fun onCallback(value: Boolean)
    }

    private fun checkUsername(callback: UserExistsCallback) {
        binding.progressBar.visibility = View.VISIBLE
        val username = binding.inpUsername.text.toString().trim()
        ref.child("Users").orderByChild("username")
            .equalTo(username).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        binding.progressBar.visibility = View.GONE
                        callback.onCallback(false)
                        binding.inpUsername.error =
                            "$username " + getString(R.string.username_exist)
                    } else {
                        binding.progressBar.visibility = View.GONE
                        callback.onCallback(true)
                        Log.d(TAG, "true")
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    throw databaseError.toException() // never ignore errors
                }
            })
    }

    private fun checkEmail() {
        binding.progressBar.visibility = View.VISIBLE
        val email = binding.inpEmail.text.toString().trim()
        ref.orderByChild("email").equalTo(email)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        binding.progressBar.visibility = View.GONE
                        binding.inpEmail.error =
                            "$email " + getString(R.string.email_already_registered)
                        return
                    } else {
                        binding.progressBar.visibility = View.GONE
                        binding.inpEmail.error = getString(R.string.email_already_registered)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {}
            })
        return
    }

    private fun registrationUser(
        name: String,
        gender: String,
        email: String,
        phone: String,
        classUser: String,
        username: String,
        password: String
    ) {
        checkUsername(object : UserExistsCallback {
            override fun onCallback(value: Boolean) {
                println("User exists: $value")
            }
        })
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                checkEmail()
                if (task.isSuccessful) {
                    saveData(name, gender, email, phone, classUser, username, password)
                } else {
                    val message = task.exception!!.toString()
                    Log.e(TAG, message)
                    Toast.makeText(
                        this,
                        getString(R.string.registration_failed),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveData(
        name: String,
        gender: String,
        email: String,
        phone: String,
        classUser: String,
        username: String,
        password: String
    ) {
        val currentUserId = auth.currentUser!!.uid
        ref = FirebaseDatabase.getInstance().reference.child("Users")
        val user = HashMap<String, Any>()
        user["id"] = currentUserId
        user["name"] = name
        user["gender"] = gender
        user["email"] = email
        user["phone"] = phone
        user["classUser"] = classUser
        user["username"] = username
        user["password"] = password

        binding.progressBar.visibility = View.VISIBLE
        ref.child(currentUserId).setValue(user).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this, getString(R.string.registration_success), Toast.LENGTH_SHORT)
                    .show()
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            } else {
                val message = task.exception!!.toString()
                Log.e(TAG, message)
                Toast.makeText(this, getString(R.string.registration_failed), Toast.LENGTH_SHORT)
                    .show()
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun editTextListener() {
        binding.apply {
            inpFullname.addTextChangedListener(object : TextWatcher {
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
            inpGender.addTextChangedListener(object : TextWatcher {
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
            inpPhone.addTextChangedListener(object : TextWatcher {
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
            inpClass.addTextChangedListener(object : TextWatcher {
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
            inpUsername.addTextChangedListener(object : TextWatcher {
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
            inpPassword.addTextChangedListener(object : TextWatcher {
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
            binding.btnSignUp.setOnClickListener {
                confirmData()
            }
        }

    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length >= 8
    }

    private fun isPhoneValid(phone: String): Boolean {
        return Patterns.PHONE.matcher(phone).matches()
    }

    private fun setBtnEnable() {
        binding.apply {
            val name = inpFullname.text.toString().trim()
            val gender = inpGender.text.toString().trim()
            val email = inpEmail.text.toString().trim()
            val phone = inpPhone.text.toString().trim()
            val classUser = inpClass.text.toString().trim()
            val username = inpUsername.text.toString().trim()
            val pass = inpPassword.text.toString().trim()

            btnSignUp.isEnabled =
                name.isNotEmpty() &&
                        gender.isNotEmpty() &&
                        email.isNotEmpty() &&
                        phone.isNotEmpty() &&
                        isPhoneValid(phone) &&
                        classUser.isNotEmpty() &&
                        username.isNotEmpty() &&
                        pass.isNotEmpty() &&
                        isPasswordValid(pass) &&
                        isEmailValid(email)
        }
    }

    companion object {
        const val TAG = "Register Activity"
    }
}