package com.example.aad_phase_2_practice_project.views.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aad_phase_2_practice_project.ApiServices.model.LearnerLeardersResponse
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.inflate
import com.example.aad_phase_2_practice_project.model.LearningLeadersEntity
import kotlinx.android.synthetic.main.learning_items.view.*

class TopLearningAdapter () : RecyclerView.Adapter<TopLearningAdapter.LearningViewHolder>() {

    private var items = ArrayList<LearnerLeardersResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopLearningAdapter.LearningViewHolder = LearningViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TopLearningAdapter.LearningViewHolder, position: Int) =
        holder.bind(items[position])

    fun addItems( list: List<LearnerLeardersResponse>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class LearningViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(
            R.layout.learning_items
        )
    ) {

        @SuppressLint("SetTextI18n")
        fun bind(items: LearnerLeardersResponse) {

            itemView.tvName.text = items.name
            itemView.tvHours.text = "${items.hours} learning hours,"
            itemView.tvCountry.text = items.country
            Glide
                .with(itemView.img)
                .load(items.badgeUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_top_learner)
                .into(itemView.img)

        }

    }
}