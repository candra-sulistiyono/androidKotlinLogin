package com.lutfi.loginapp.screens.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.lutfi.loginapp.R
import com.lutfi.loginapp.api.ApiInterface
import com.lutfi.loginapp.model.RequestModel
import com.lutfi.loginapp.model.ResponseModel
import com.lutfi.loginapp.screens.welcome.WelcomeActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val apiClient by lazy {
        ApiInterface.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initAction()
    }

    private fun initAction() {
        btnLogin.setOnClickListener {
            val username = tilEmail.editText?.text.toString()
            val password = tilPassword.editText?.text.toString()
            if (username.isNotEmpty() && password.isNotEmpty()) {
                login(username, password)
            } else {
                Toast.makeText(this, "Username/Password Cannot be Empty!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btnRegister.setOnClickListener {
            Toast.makeText(this, "Register Button Clicked", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun login(username: String, password: String) {
        apiClient.login(RequestModel.Login(username, password)).enqueue(object :
            Callback<ResponseModel.Login> {
            override fun onFailure(call: Call<ResponseModel.Login>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error Login", Toast.LENGTH_SHORT).show()
                Log.d("Login : ", t.message)
                hideKeyboard()
            }

            override fun onResponse(
                call: Call<ResponseModel.Login>,
                response: Response<ResponseModel.Login>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Login Success", Toast.LENGTH_SHORT).show()
                    Log.d("Login : ", response.code().toString())
                    Log.d("User : ", response.body()?.user?.token.toString())
                    WelcomeActivity.start(this@MainActivity, response.body()?.user!!)
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Wrong Email/Password", Toast.LENGTH_SHORT)
                        .show()
                    Log.d("Login : ", response.code().toString())
                    hideKeyboard()
                }
            }

        })
    }

    private fun hideKeyboard() {
        val inputManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        var iBinder: IBinder? = null
        try {
            iBinder = currentFocus!!.windowToken
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        if (iBinder != null) {
            inputManager.hideSoftInputFromWindow(
                iBinder,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }

    }
}
