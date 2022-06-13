package com.example.mainuiux.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    val id: String,
    val name: String,
    val gender: String,
    val email: String,
    val phone: String,
    val classUser: String,
    val username: String,
    val password: String,
) : Parcelable {
    constructor() : this("", "", "", "", "", "", "", "")
}
