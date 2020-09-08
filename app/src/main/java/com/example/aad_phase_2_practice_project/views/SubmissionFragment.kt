package com.example.aad_phase_2_practice_project.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.aad_phase_2_practice_project.ApiServices.RetrofitClient
import com.example.aad_phase_2_practice_project.R
import com.example.aad_phase_2_practice_project.views.dialogs.AreYouSureDialogFragment
import com.example.aad_phase_2_practice_project.views.dialogs.YesDialogClick
import kotlinx.android.synthetic.main.fragment_skill_i_q.*
import kotlinx.android.synthetic.main.fragment_submission.*
import kotlinx.android.synthetic.main.fragment_submission.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [SubmissionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SubmissionFragment : Fragment(), YesDialogClick {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submission, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AreYouSureDialogFragment.setYesDialogClick(this)

        btnSubmit.setOnClickListener {
            val firstName = etFname.text.toString().trim()
            val lastName = etLname.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val link = etProject.text.toString().trim()
            when {
                firstName.isBlank()  -> {
                    etFname.error = "Enter first name"
                    etFname.requestFocus()
                    return@setOnClickListener
                }
                lastName.isBlank()  -> {
                    etLname.error = "Enter last name"
                    etLname.requestFocus()
                    return@setOnClickListener
                }
                link.isBlank() -> {
                    etProject.error = "Enter link to you Github"
                    etProject.requestFocus()
                    return@setOnClickListener
                }
                email.isBlank() -> {
                    etEmail.error = "Enter Email"
                    etEmail.requestFocus()
                    return@setOnClickListener
                }

            }

              findNavController().navigate(R.id.to_areYouSureDialogFragment)
        }
    }

    private fun handleSubmission(){
        val firstName = etFname.text.toString().trim()
        val lastName = etLname.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val link = etProject.text.toString().trim()
        RetrofitClient.submitInstance.submission(firstName,lastName,email, link)

            .enqueue(object: Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
                    println("Submission Failure: ${t.message}")
                    submitProgressBar.visibility = View.GONE
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {

                    if(response.isSuccessful){
                        submitProgressBar.visibility = View.GONE
                        findNavController().navigate(R.id.to_dialogSubmissionSuccessfulFragment)
                        /*val intent = Intent(requireContext(), MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(intent)*/

                    }else{
                        submitProgressBar.visibility = View.GONE
                        findNavController().navigate(R.id.to_dialogSubmissionNotSuccessfulFragment)
                        Toast.makeText(requireContext(), "Submission Fail: ${response.errorBody()}", Toast.LENGTH_LONG).show()
                        println("Submission Fail: ${response.body()}")
                    }

                }
            })
    }

    override fun onStart() {
        super.onStart()
        MainActivity.hideToolBarTitle(requireActivity())
        showSubmitView(requireActivity())
    }

    override fun onResume() {
        super.onResume()
        showSubmitView(requireActivity())
    }


    override fun getSelected() {
        handleSubmission()
        submitProgressBar.visibility = View.VISIBLE
    }

    fun hideSubmitView(){

    }

    companion object{
        //val view: View? = null
        fun hideSubmitView(activity: FragmentActivity){
            activity.views.visibility = View.GONE
        }
        fun showSubmitView(activity: FragmentActivity){
            activity.views.visibility = View.VISIBLE

        }
    }

}