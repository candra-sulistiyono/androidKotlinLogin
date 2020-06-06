package com.lutfi.loginapp.model

import com.google.gson.annotations.SerializedName

object RequestModel {
    data class Login(
        @SerializedName("email")
        var username: String,

        @SerializedName("password")
        var password: String
    )
}