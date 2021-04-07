package com.example.diverseapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.diverseapp.R

//Not included in project
class OngoingTasksFragment : Fragment() {

    companion object {
        fun getInstance(): OngoingTasksFragment {
            val fragment =
                OngoingTasksFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ongoing_tasks, container, false)
    }

}