package com.lutfi.loginapp.model

import com.google.gson.annotations.SerializedName

object ResponseModel {
    data class Login(
        @SerializedName("code")
        var code: Int,

        @SerializedName("data")
        var user: AppModel.User?,

        @SerializedName("message")
        var msg: String,

        @SerializedName("status")
        var status: String
    )
}