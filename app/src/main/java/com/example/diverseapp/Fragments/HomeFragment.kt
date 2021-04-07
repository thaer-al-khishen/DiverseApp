package com.example.diverseapp.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Activities.CategoryDetailsActivity
import com.example.diverseapp.Adapters.*
import com.example.diverseapp.Activities.MainActivity
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.fragment_home.*

//Your landing page fragment
class HomeFragment : Fragment() {
    companion object {
        fun getInstance(): HomeFragment {
            val fragment =
                HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance() = HomeFragment()
    }


    //Instantiate the adapter for the recyclerview directly below the Categories textview
    private lateinit var categoriesAdapter: CategoriesAdapter

    //Instantiate the adapter for the recyclerview directly below the Recommended textview
    private lateinit var recommendedAdapter: RecommendedAdapter

    //Instantiate the adapter for the recyclerview directly below the Moving into a new home textview
    private lateinit var movingAdapter: MovingAdapter

    //Instantiate the adapter for the recyclerview directly below the Essential home services textview (Same as the above adapter)
    private lateinit var essentialAdapter: MovingAdapter

    //Instantiate the adapter for the recyclerview directly below the Outdoor upkeep textview (Same as the above adapter)
    private lateinit var outdoorAdapter: MovingAdapter

    //Instantiate the arraylist for the items in the categories recyclerview
    private val categories = ArrayList<String>()

    //Instantiate the arraylist for the items in the recommended recyclerview
    private val recommended = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideSoftKeyboard()
        setupSpinner()
        setupRecyclerView()
    }

    fun setupRecyclerView(){

        //Setup adapter for the categories recyclerview
        categoriesAdapter = CategoriesAdapter(categories)
        categoriesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoriesRecyclerView.adapter = categoriesAdapter

        //Populate the arraylist of the categories
        categories.clear()
        categories.add("Outdoor Upkeep")
        categories.add("Handyman")
        categories.add("Interior painting")
        categories.add("Home Cleaning")

        //Onclicklistener from recyclerview item to another activity
        categoriesAdapter.setOnSettingSelectedListener(object : CategoriesAdapter.ISettingSelected {
            override fun onItemClicked(item: String) {
                when {
                    item == "Outdoor Upkeep" -> {
                        val intent = Intent(activity, CategoryDetailsActivity::class.java)
                        startActivity(intent)
                    }

                }
            }
        })

        //Setup adapter for the recommended recyclerview
        recommendedAdapter = RecommendedAdapter(recommended)
        recommendedRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recommendedRecyclerView.adapter = recommendedAdapter

        //Populate the arraylist of the recommended items
        recommended.clear()
        recommended.add("Full Service Lawn Care")
        recommended.add("Plumbing drain repair")
        recommended.add("AC Maintenance")
        recommended.add("Light Bulb Replacement")

        //Setup adapter for the moving into... recyclerview
        movingAdapter = MovingAdapter(recommended)
        movingRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        movingRecyclerView.adapter = movingAdapter

        //Setup adapter for the essential recyclerview
        essentialAdapter = MovingAdapter(recommended)
        essentialRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        essentialRecyclerView.adapter = essentialAdapter

        //Setup adapter for the outdoor recyclerview
        outdoorAdapter = MovingAdapter(recommended)
        outdoorRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        outdoorRecyclerView.adapter = outdoorAdapter

    }

    //Prepopulate the spinner at the top of the page
    fun setupSpinner(){
        val languages = resources.getStringArray(R.array.Languages)

        if (locationSpinner != null) {
            val adapter = ArrayAdapter((activity as MainActivity),
                android.R.layout.simple_spinner_dropdown_item, languages)
            locationSpinner.adapter = adapter

            locationSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Performs search when user hit the search button on the keyboard
                return false
            }
            override fun onQueryTextChange(p0: String?): Boolean {
                //Start filtering the list as user start entering the characters
//                adapter.filter.filter(p0)
                return false
            }
        })
    }

    private fun hideSoftKeyboard() {
        if (activity?.currentFocus != null) {
            val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            inputMethodManager!!.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, 0)
        }
    }

}