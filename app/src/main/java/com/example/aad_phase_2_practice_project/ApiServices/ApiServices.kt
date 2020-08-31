package com.example.aad_phase_2_practice_project.ApiServices

import com.example.aad_phase_2_practice_project.ApiServices.model.LearnerLeardersResponse
import com.example.aad_phase_2_practice_project.ApiServices.model.SkillIQLearnersResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("api/hours")
    fun getLearningLeaders(): Call<List<LearnerLeardersResponse>>

    @GET("api/hours")
    fun getSkillIqLeaders(): Call<List<SkillIQLearnersResponse>>
}