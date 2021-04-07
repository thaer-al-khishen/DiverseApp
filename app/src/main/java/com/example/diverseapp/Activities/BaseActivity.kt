package com.example.diverseapp.Activities

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

//Use as a blueprint for the MainActivity though not necessary in this particular case
abstract class BaseActivity: AppCompatActivity() {

    //The function that implements the switching of fragments with replacement
    fun replaceFragment(containerId: Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(containerId, fragment).addToBackStack("root_fragment").commit()
        supportFragmentManager.addOnBackStackChangedListener {
        }
    }

}
