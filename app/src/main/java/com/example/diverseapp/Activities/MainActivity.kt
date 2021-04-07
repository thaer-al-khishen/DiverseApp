package com.example.diverseapp.Activities

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.diverseapp.Fragments.*
import com.example.diverseapp.Models.Card
import com.example.diverseapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    //Instantiate all the fragments to be used in the app
    private val allCategoriesFragment = AllCategoriesFragment()
    private val homeFragment = HomeFragment()
    private val tasksFragment = TasksFragment()
    private val profileFragment = ProfileFragment()
    private val inboxFragment = InboxFragment()
    private var currentFragment: FragmentType =
        FragmentType.HOME
    private var atHome = true

    companion object {
        enum class FragmentType {
            HOME,
            TASKS,
            INBOX,
            PROFILE,
            AllCATEGORIES,
            CARDSLIST,
            CARDDETAILS,
            ABOUT
        }

        private const val BACK_STACK_ROOT_TAG = "root_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavView.setOnNavigationItemReselectedListener { }
        if (savedInstanceState == null) {
            showHomeFragment()
        } else {
            atHome = savedInstanceState.getBoolean("atHome")
        }
        supportActionBar?.hide()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    popBackStack()
                    switch(FragmentType.HOME, null)
                    atHome = true
                    return@OnNavigationItemSelectedListener true
                }
                R.id.tasksFragment -> {
                    popBackStack()
                    switch(FragmentType.TASKS, null)
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.allCategoriesFragment -> {
                    popBackStack()
                    switch(FragmentType.AllCATEGORIES, null)
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.inboxFragment -> {
                    popBackStack()
                    switch(FragmentType.INBOX, null)
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
                R.id.profileFragment -> {
                    popBackStack()
                    switch(FragmentType.PROFILE, null)
                    atHome = false
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    //Handle onbackpressed going back to one of the 5 bottom navigation fragments taking into consideration the highlighted bottom navigation item
    override fun onBackPressed() {
        super.onBackPressed()

        when {
            homeFragment.isVisible -> {
                bottomNavView.selectedItemId =
                    R.id.homeFragment
            }
            tasksFragment.isVisible -> {
                bottomNavView.selectedItemId =
                    R.id.tasksFragment
            }
            allCategoriesFragment.isVisible -> {
                bottomNavView.selectedItemId =
                    R.id.allCategoriesFragment
            }
            inboxFragment.isVisible -> {
                bottomNavView.selectedItemId =
                    R.id.inboxFragment
            }
            profileFragment.isVisible -> {
                bottomNavView.selectedItemId =
                    R.id.profileFragment
            }
        }
    }

    //Function that handles the switching to each fragment
    //Note that transitioning to any of the 5 bottom fragments is done with add not replace
    //Replace is called in all other cases
    fun switch(
        type: FragmentType,
        card: Card?,
    ) {
        when (type) {

            FragmentType.HOME -> {
                popBackStack()
            }

            FragmentType.TASKS -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, tasksFragment)
                    .hide(homeFragment)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit()
            }

            FragmentType.INBOX -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, inboxFragment)
                    .hide(homeFragment)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit()
            }

            FragmentType.AllCATEGORIES -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, allCategoriesFragment)
                    .hide(homeFragment)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit()
            }

            FragmentType.PROFILE -> {
                supportFragmentManager.beginTransaction()
                    .add(R.id.mainContainer, profileFragment)
                    .hide(homeFragment)
                    .addToBackStack(BACK_STACK_ROOT_TAG)
                    .commit()
            }

            FragmentType.CARDSLIST -> {
                replaceFragment(R.id.mainContainer, CardsListFragment.newInstance())
            }

            FragmentType.CARDDETAILS -> {
                card?.let {
                    replaceFragment(R.id.mainContainer, CardDetailsFragment.newInstance(it))
                }
            }

            FragmentType.ABOUT -> {
                replaceFragment(R.id.mainContainer, CardsListFragment.newInstance())
            }

        }
        currentFragment = type
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.mainContainer, homeFragment)
            .commit()
        supportFragmentManager.executePendingTransactions()
    }

    private fun popBackStack() {
        supportActionBar?.hide()
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}