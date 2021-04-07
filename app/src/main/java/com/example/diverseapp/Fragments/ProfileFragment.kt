package com.example.diverseapp.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Activities.AboutActivity
import com.example.diverseapp.Activities.DraggingActivity
import com.example.diverseapp.Adapters.ProfileAdapter
import com.example.diverseapp.Adapters.SecondProfileAdapter
import com.example.diverseapp.Activities.MainActivity
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.fragment_settings.*

//Clicking on the 5th last item in the bottomnav takes you to this fragment
class ProfileFragment : Fragment() {
    companion object {
        fun getInstance(): ProfileFragment {
            val fragment =
                ProfileFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }

        fun newInstance() = ProfileFragment()
    }

    //Declare a variable for each adapter
    private lateinit var firstProfileAdapter: ProfileAdapter
    private lateinit var secondProfileAdapter: SecondProfileAdapter

    //Declare a variable for each arraylist to be used in the recyclerviews
    private val accounts = ArrayList<String>()
    private val support = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Should be renamed to fragment_profile
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.hide()
        setRecyclerViews()
    }

    private fun setRecyclerViews() {

        //Setup the ProfileAdapter
        firstProfileAdapter = ProfileAdapter(accounts)
        accountRecyclerView?.layoutManager = LinearLayoutManager(context)
        accountRecyclerView?.adapter = firstProfileAdapter

        //Populate the accounts arraylist (First recyclerview)
        accounts.clear()
        accounts.add("Account")
        accounts.add("Payment Methods")
        accounts.add("Notifications")

        //Populate the support arraylist (Second recyclerview)
        support.clear()
        support.add("Help")
        support.add("Privacy Policy")
        support.add("Terms Of Use")
        support.add("Log Out")

        //Set the onclicklistener for the first recyclerview
        firstProfileAdapter.setOnSettingSelectedListener(object : ProfileAdapter.ISettingSelected {
            override fun onItemClicked(item: String) {
                when {
                    item == "Account" -> {
                        val intent = Intent(activity, AboutActivity::class.java)
                        startActivity(intent)
                    }
                    item == "Payment Methods" -> {
                        val intent = Intent(activity, DraggingActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        })

        //Setup the adapter for the second recyclerview
        secondProfileAdapter = SecondProfileAdapter(support)
        supportRecyclerView?.layoutManager = LinearLayoutManager(context)
        supportRecyclerView?.adapter = secondProfileAdapter

    }

}