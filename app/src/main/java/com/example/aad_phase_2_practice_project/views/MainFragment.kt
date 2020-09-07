package com.example.aad_phase_2_practice_project.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.views.adapter.LeadersPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_main.*


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {

    var titles = arrayOf("Learning Leaders","Skill IQ Leaders")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupViewPager(viewpager)
        // attaching tab mediator
        TabLayoutMediator(tabLayout, viewpager,
            TabLayoutMediator.TabConfigurationStrategy { tab: TabLayout.Tab, position: Int ->
                tab.text = titles[position]
            }
        ).attach()


    }

    private fun setupViewPager(viewPager: ViewPager2) {
        val pagerAdapter = LeadersPager(requireActivity())
        pagerAdapter.addFragment(TopLearnersFragment(),"Learning Leaders")
        pagerAdapter.addFragment(SkillIQFragment(), "Previous shipments")

        //adding pageradapter to viewpager
        viewPager.adapter = pagerAdapter
    }

    override fun onStart() {
        super.onStart()
        MainActivity.updateToolBarTitle(requireActivity(),"")
        MainActivity.showBarTitle(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        MainActivity.updateToolBarTitle(requireActivity(),"")

    }
}