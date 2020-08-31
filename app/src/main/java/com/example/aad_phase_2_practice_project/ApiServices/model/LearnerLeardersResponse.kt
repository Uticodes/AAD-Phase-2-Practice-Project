package com.example.aad_phase_2_practice_project.ApiServices.model

import com.google.gson.annotations.SerializedName

data class LearnerLeardersResponse(
    @SerializedName("name") var name : String,
    @SerializedName("hours") var hours : Int,
    @SerializedName("country") var country : String,
    @SerializedName("badgeUrl") var badgeUrl : String
)