package com.example.diverseapp.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Fragments.*
import com.example.diverseapp.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_category_details.*

//The result of pressing on the recyclerview found under the categories textview in the homefragment under the blue area at the top
class CategoryDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_details)

        //Add 3 tabs to the tablayout
        tabLayout?.addTab(tabLayout.newTab())
        tabLayout?.addTab(tabLayout.newTab())
        tabLayout?.addTab(tabLayout.newTab())

        //Setup the layout manager for the viewpager
        val mLayoutManager = LinearLayoutManager(this)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        viewPager?.adapter = PagerAdapter(supportFragmentManager, tabLayout!!.tabCount)

        //Implement the ability to perform an action after pressing the tab
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewPager)

        //Set the text for each tab
        tabLayout.getTabAt(0)?.setText("All");
        tabLayout.getTabAt(1)?.setText("Carousel");
        tabLayout.getTabAt(2)?.setText("Grid");

        //Setup a margin between the tabs of the tablayout
        for (i in 0 until tabLayout.getTabCount()) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(0, 0, 30, 0)
            tab.requestLayout()
        }

        //Specify what to do after each tab is selected, unselected, and reselected.
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.currentItem = tab.position

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    class PagerAdapter(fm: FragmentManager?, var mNumOfTabs: Int) :
        FragmentPagerAdapter(fm!!, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {

                //This is where you declare the destination fragments which will be the result of the onclick of every tab

                0 -> CategoryDetailsAllFragment.getInstance()
                1 -> CarouselFragment.getInstance()
                2 -> GridFragment.getInstance()

                else -> FirstTabFragment.getInstance()
            }
        }

        override fun getCount(): Int {
            //The number of tabs to be used
            return 3
        }
    }

    override fun onBackPressed() {
        if ( supportFragmentManager.backStackEntryCount > 0)
        {
            supportFragmentManager.popBackStack();
            return;
        }
        super.onBackPressed();
    }

}