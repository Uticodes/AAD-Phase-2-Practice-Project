package com.example.aad_phase_2_practice_project.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aad_phase_2_practice_project.ApiServices.ApiServices
import com.example.aad_phase_2_practice_project.ApiServices.model.SkillIQLearnersResponse
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.getRetrofit
import com.example.aad_phase_2_practice_project.model.SkillIQLeadersEntity
import com.example.aad_phase_2_practice_project.views.adapter.SkillIQLeadersAdapter
import kotlinx.android.synthetic.main.skilliq_items.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [SkillIQFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SkillIQFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private var skillIQLeadersAdapter = SkillIQLeadersAdapter()
    private var allSkillIq: List<SkillIQLeadersEntity>? = null
    lateinit var layoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_skill_i_q, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.skillIqRecycler)
        recyclerView.adapter = skillIQLeadersAdapter
        layoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = layoutManager

        val service = getRetrofit.create(ApiServices::class.java)
        val call = service.getSkillIqLeaders()

        call.enqueue(object : Callback<List<SkillIQLearnersResponse>> {
            override fun onResponse(call: Call<List<SkillIQLearnersResponse>>, response: Response<List<SkillIQLearnersResponse>>) {
                if (response.code() == 200) {
                    val skillIq = response.body()!!
                    skillIQLeadersAdapter.addItems(response.body()!!)

                }

            }

            override fun onFailure(call: Call<List<SkillIQLearnersResponse>>, t: Throwable) {
                tvIqName!!.text = t.message
                tvIqCountry!!.text = t.message
                tvIqScores!!.text = t.message
                //imgIq.setImageResource(R.drawable) = t.message
            }
        })
    }
}
