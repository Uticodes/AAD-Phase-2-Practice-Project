package com.example.aad_phase_2_practice_project.ApiServices

import com.example.aad_phase_2_practice_project.ApiServices.model.LearnerLeardersResponse
import com.example.aad_phase_2_practice_project.ApiServices.model.LoginResponse
import com.example.aad_phase_2_practice_project.ApiServices.model.SkillIQLearnersResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiServices {

    @GET("api/hours")
    fun getLearningLeaders(): Call<List<LearnerLeardersResponse>>

    @GET("api/skilliq")
    fun getSkillIqLeaders(): Call<List<SkillIQLearnersResponse>>

    @FormUrlEncoded
    @POST("auth/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    fun submission(
        @Field("entry.1824927963") email: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") linkToProject: String
    ): Call<Void>
}