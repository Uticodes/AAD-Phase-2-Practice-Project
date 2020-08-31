package com.example.aad_phase_2_practice_project.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aad_phase_2_practice_project.ApiServices.ApiServices
import com.example.aad_phase_2_practice_project.ApiServices.model.LearnerLeardersResponse
import com.example.aad_phase_2_practice_project.ApiServices.model.SkillIQLearnersResponse
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.getRetrofit
import com.example.aad_phase_2_practice_project.model.LearningLeadersEntity
import com.example.aad_phase_2_practice_project.model.SkillIQLeadersEntity
import com.example.aad_phase_2_practice_project.views.adapter.SkillIQLeadersAdapter
import com.example.aad_phase_2_practice_project.views.adapter.TopLearningAdapter
import kotlinx.android.synthetic.main.learning_items.*
import kotlinx.android.synthetic.main.skilliq_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [LeardershipFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopLearnersFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private var topLearnersAdapter = TopLearningAdapter()
    private var allTopLearners: List<LearningLeadersEntity>? = null
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_learners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.topLearnersRecycler)
        recyclerView.adapter = topLearnersAdapter
        layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        val service = getRetrofit.create(ApiServices::class.java)
        val call = service.getLearningLeaders()

        call.enqueue(object : Callback<List<LearnerLeardersResponse>> {
            override fun onResponse(call: Call<List<LearnerLeardersResponse>>, response: Response<List<LearnerLeardersResponse>>) {
                if (response.code() == 200) {
                    val skillIq = response.body()!!
                    topLearnersAdapter.addItems(response.body()!!)

                }

            }

            override fun onFailure(call: Call<List<LearnerLeardersResponse>>, t: Throwable) {
                tvName!!.text = t.message
                tvCountry!!.text = t.message
                tvHours!!.text = t.message
                //imgIq.setImageResource(R.drawable) = t.message
            }
        })
    }


}