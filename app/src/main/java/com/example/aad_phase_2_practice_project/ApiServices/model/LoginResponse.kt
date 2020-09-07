package com.example.aad_phase_2_practice_project.ApiServices.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token") var token : String,
    @SerializedName("token_type") var token_type : String,
    @SerializedName("expires_in") var expires_in : Int,
    @SerializedName("user") var user : User
)

data class User(
    @SerializedName("id") val id:Int,
    @SerializedName("username") val username:String,
    @SerializedName("email") val email:String)