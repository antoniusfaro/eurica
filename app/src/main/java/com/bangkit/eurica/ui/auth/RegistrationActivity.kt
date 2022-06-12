package com.bangkit.eurica.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bangkit.eurica.databinding.ActivityRegistrationBinding
import com.bangkit.eurica.network.ApiConfig
import com.bangkit.eurica.network.RegisterResponse
import com.bangkit.eurica.network.UserResponse
import com.bangkit.eurica.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val inputUsername = binding.regUsername.text.toString()
            val inputPassword = binding.regPassword.text.toString()
            val inputPasswordConfirmation = binding.regPasswordConfirmation.text.toString()
            if (inputUsername != "" && inputPassword != "" && inputPasswordConfirmation != "") {
                register(inputUsername, inputPassword, inputPasswordConfirmation)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please fill all empty field",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun register(name: String, password: String, confirm_password: String) {
        val client = ApiConfig.getAuthApiService().registerUser(name, password, confirm_password)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if (responseBody.affectedRows == "1") {
                            Toast.makeText(
                                applicationContext,
                                "Registration was success, please login",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(applicationContext, LoginActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Registration failed, username was already in used!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
                Toast.makeText(
                    applicationContext,
                    "Registration failed, something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

    companion object {
        private const val TAG = "RegistrationActivity"
    }

}
