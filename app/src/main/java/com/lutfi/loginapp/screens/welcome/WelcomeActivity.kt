package com.lutfi.loginapp.screens.welcome

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lutfi.loginapp.R
import com.lutfi.loginapp.model.AppModel
import kotlinx.android.synthetic.main.activity_welcome.*


class WelcomeActivity : AppCompatActivity() {

    var user: AppModel.User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        initIntent()
        initUi()
    }

    companion object {
        fun start(context: Context, user: AppModel.User) {
            val starter = Intent(context, WelcomeActivity::class.java)
            starter.putExtra("user", user)
            context.startActivity(starter)
        }
    }

    private fun initIntent() {
        user = intent.getParcelableExtra("user")
    }

    private fun initUi() {
        tvWelcome.text = getString(R.string.text_welcome, user?.name)
        tvDataUser.text = getString(R.string.text_data_user, user?.email, user?.token)
    }


}
