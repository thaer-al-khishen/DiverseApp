package com.example.diverseapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.InboxAdapter
import com.example.diverseapp.Activities.MainActivity
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.fragment_inbox.*

class InboxFragment : Fragment() {
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

    //Declare a variable for the inboxAdapter
    private lateinit var inboxAdapter: InboxAdapter

    //Declare a variable for the userNames Arraylist
    private val userNames = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inbox, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerViews()
    }

    private fun setRecyclerViews() {
        //Setup the InboxAdapter
        inboxAdapter = InboxAdapter(userNames)
        inboxRecyclerView?.layoutManager = LinearLayoutManager(context)
        inboxRecyclerView?.adapter = inboxAdapter

        //Populate the userNames Arraylist
        userNames.clear()
        userNames.add("Lawn Starter Lawn Care")
        userNames.add("Carpet Cleaning")
        userNames.add("Math Tutoring")
        userNames.add("Spanish Lessons")
        userNames.add("Handyman")
    }



}