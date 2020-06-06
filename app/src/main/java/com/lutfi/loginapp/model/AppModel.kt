package com.lutfi.loginapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

object AppModel {
    @Parcelize
    data class User(
        @SerializedName("email")
        var email: String,

        @SerializedName("name")
        var name: String,

        @SerializedName("token")
        var token: String
    ) : Parcelable
}