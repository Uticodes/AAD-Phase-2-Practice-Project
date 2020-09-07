package com.example.aad_phase_2_practice_project.views

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.aad_phase_2_practice_project.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        tvSubmit.setOnClickListener {
            navController.navigate(R.id.to_submissionFragment)
        }

       /* supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)*/
    }


    companion object {
        fun updateToolBarTitle(activity: Activity, title: String) {
            activity.main_toolbar.title = title
        }
        fun hideToolBarTitle(activity: Activity) {
            activity.leaderAppBar.visibility = View.GONE
            activity.submitAppBar.visibility = View.VISIBLE
        }
        fun showBarTitle(activity: Activity) {
            activity.leaderAppBar.visibility = View.VISIBLE
            activity.submitAppBar.visibility = View.GONE
        }
    }
}