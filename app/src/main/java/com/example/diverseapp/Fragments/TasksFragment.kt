package com.example.diverseapp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diverseapp.Activities.MainActivity
import com.example.diverseapp.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_tasks.*

class TasksFragment : Fragment() {
    companion object {
        fun getInstance(): TasksFragment {
            val fragment =
                TasksFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
        fun newInstance() = TasksFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.hide()

        //Add 4 tabs to the tablayout
        tabLayout?.addTab(tabLayout.newTab())
        tabLayout?.addTab(tabLayout.newTab())
        tabLayout?.addTab(tabLayout.newTab())
        tabLayout?.addTab(tabLayout.newTab())

        //Setup the layout manager for the viewpager
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        viewPager?.adapter = PagerAdapter(childFragmentManager, tabLayout!!.tabCount)

        //Implement the ability to perform an action after pressing the tab
        viewPager?.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewPager)

        //Set the text for each tab
        tabLayout.getTabAt(0)?.setText("Ongoing");
        tabLayout.getTabAt(1)?.setText("Carousel");
        tabLayout.getTabAt(2)?.setText("Grid");
        tabLayout.getTabAt(3)?.setText("Slider");

        //Setup a margin between the tabs of the tablayout
        for (i in 0 until tabLayout.getTabCount()) {
            val tab = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 50, 0)
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
                3 -> SliderFragment.getInstance()

                else -> FirstTabFragment.getInstance()
            }
        }

        override fun getCount(): Int {
            //The number of tabs to be used
            return 4
        }
    }
}