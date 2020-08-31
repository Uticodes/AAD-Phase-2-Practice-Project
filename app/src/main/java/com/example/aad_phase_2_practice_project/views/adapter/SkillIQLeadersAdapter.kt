package com.example.aad_phase_2_practice_project.views.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aad_phase_2_practice_project.ApiServices.model.SkillIQLearnersResponse
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.inflate
import com.example.aad_phase_2_practice_project.model.SkillIQLeadersEntity
import kotlinx.android.synthetic.main.skilliq_items.view.*

class SkillIQLeadersAdapter () : RecyclerView.Adapter<SkillIQLeadersAdapter.SkillIQViewHolder>() {

    private var items = ArrayList<SkillIQLearnersResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SkillIQLeadersAdapter.SkillIQViewHolder = SkillIQViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: SkillIQLeadersAdapter.SkillIQViewHolder, position: Int) =
        holder.bind(items[position])

    fun addItems( list: List<SkillIQLearnersResponse>) {
        this.items.clear()
        this.items.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

    inner class SkillIQViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(
            R.layout.skilliq_items
        )
    ) {

        fun bind(items: SkillIQLearnersResponse) {

            itemView.tvIqName.text = items.name
            itemView.tvIqScores.text = items.score.toString()
            itemView.tvIqCountry.text = items.country
            Glide
                .with(itemView.imgIq)
                .load(items.badgeUrl)
                .centerCrop()
                .placeholder(R.drawable.ic_skill_iq)
                .into(itemView.imgIq)

        }

    }
}