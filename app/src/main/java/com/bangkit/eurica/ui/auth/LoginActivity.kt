package com.bangkit.eurica.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bangkit.eurica.databinding.ActivityLoginBinding
import com.bangkit.eurica.model.UserData
import com.bangkit.eurica.network.ApiConfig
import com.bangkit.eurica.network.UserResponse
import com.bangkit.eurica.ui.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
            login()
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun login() {
        val inputUsername = binding.tiUsername.text.toString()
        val inputPassword = binding.tiPassword.text.toString()

        val client = ApiConfig.getAuthApiService().getListUsers()
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    var isUserFound = false
                    var username = ""
                    if (responseBody != null) {
                        for (user in responseBody.data) {
                            if (user.name == inputUsername && user.password == inputPassword) {
                                isUserFound = true
                                username = user.name
                            }
                            Log.d("WOIIIIIIIIIIIIIIII!!!", "$user")
                            Log.d("SERU!!!", "${user.name} ${user.password}")
                        }
                        if (isUserFound) {
                            Toast.makeText(
                                applicationContext,
                                "Login Successfully, Welcome to Eurica",
                                Toast.LENGTH_SHORT
                            ).show()

                            val intent = Intent(applicationContext, MainActivity::class.java)
                            intent.putExtra(MainActivity.EXTRA_NAME, username)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                applicationContext,
                                "Username or Password Wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        Log.d(TAG, "BERHASIL: ${responseBody.data}")
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "MainActivity"
    }

}