package com.example.aad_phase_2_practice_project.views

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aad_phase_2_practice_project.ApiServices.ApiServices
import com.example.aad_phase_2_practice_project.ApiServices.model.LearnerLeardersResponse
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.getRetrofit
import com.example.aad_phase_2_practice_project.model.LearningLeadersEntity
import com.example.aad_phase_2_practice_project.views.adapter.TopLearningAdapter
import kotlinx.android.synthetic.main.fragment_top_learners.*
import kotlinx.android.synthetic.main.fragment_top_learners.view.*
import kotlinx.android.synthetic.main.learning_items.*
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

        topProgressBar.visibility = View.VISIBLE
        top_learners_empty_text.visibility = View.VISIBLE

        call.enqueue(object : Callback<List<LearnerLeardersResponse>> {
            override fun onResponse(call: Call<List<LearnerLeardersResponse>>, response: Response<List<LearnerLeardersResponse>>) {
                if (response.code() == 200) {
                    val skillIq = response.body()!!
                    topProgressBar.visibility = View.GONE
                    topLearnersAdapter.addItems(response.body()!!)
                    top_learners_empty_text.visibility = View.GONE

                }

            }


            override fun onFailure(call: Call<List<LearnerLeardersResponse>>, t: Throwable) {
                tvName!!.text = t.message
                tvCountry!!.text = t.message
                tvHours!!.text = t.message
                topProgressBar.visibility = View.GONE
                top_learners_empty_text.visibility = View.VISIBLE
                //imgIq.setImageResource(R.drawable) = t.message
            }
        })
    }


}