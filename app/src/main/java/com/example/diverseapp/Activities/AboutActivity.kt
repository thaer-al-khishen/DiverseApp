package com.example.diverseapp.Activities

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Adapters.FeaturedAdapter
import com.example.diverseapp.Adapters.MovingAdapter
import com.example.diverseapp.Adapters.ReviewsAdapter
import com.example.diverseapp.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.fragment_home.*

class AboutActivity : AppCompatActivity() {

    private lateinit var featuredAdapter: FeaturedAdapter
    private val featuredList = ArrayList<String>()

    private lateinit var reviewsAdapter: ReviewsAdapter
    private val reviewsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.hide()
        setupRecyclerViews()
    }


    fun setupRecyclerViews(){
        featuredList.clear()
        featuredList.add("The grass is always greener for katie")
        featuredList.add("Plumbing drain repair")
        featuredList.add("AC Maintenance")
        featuredList.add("Light Bulb Replacement")

        featuredAdapter = FeaturedAdapter(featuredList)
        featuredProjectsRecyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        featuredProjectsRecyclerview.adapter = featuredAdapter

        reviewsList.clear()
        reviewsList.add("Georgie Johnston")
        reviewsList.add("Louis Davis")
        reviewsList.add("Rose Kelley")

        reviewsAdapter = ReviewsAdapter(reviewsList)
        reviewsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        reviewsRecyclerView.adapter = reviewsAdapter
    }

    override fun onBackPressed() {
        if ( supportFragmentManager.getBackStackEntryCount() > 0)
        {
            supportFragmentManager.popBackStack();
            return;
        }
        super.onBackPressed();
    }
}