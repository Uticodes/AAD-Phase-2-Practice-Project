package com.example.aad_phase_2_practice_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val BaseUrl = "https://gadsapi.herokuapp.com/"
val HamletBaseUrl = "https://hamlet.payfill.co/api/"
val getRetrofit = Retrofit.Builder()
    .baseUrl(BaseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val getHamRetrofit = Retrofit.Builder()
    .baseUrl(HamletBaseUrl)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
